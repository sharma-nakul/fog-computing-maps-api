package fog.maps.api.service;

import fog.maps.api.error.AppException;
import fog.maps.api.internal.*;
import fog.maps.api.model.Error;
import fog.maps.api.model.direction.DirectionResult;
import fog.maps.api.model.direction.DirectionRoute;
import fog.maps.api.model.fognode.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nakulsharma on 22-11-2016.
 * Implements methods of IPollutionCheck interface
 */

@Service
public class ApiServiceImpl implements IApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    RestTemplate template;

    @Override
    public ResponseEntity getBestRoute(FogConfig config, MapsConfig mapsConfig, String mapRequestQuery) {
        LOG.info("Server is fetching the best route based on pollution ratings received from fog node");
        return getRating(RatingType.BEST_RATING, config, mapsConfig, mapRequestQuery);
    }

    @Override
    public ResponseEntity getRouteRatings(FogConfig fogConfig, MapsConfig mapsConfig, String mapRequestQuery) {
        LOG.info("Server is fetching pollution ratings for all routes");
        return getRating(RatingType.ALL_RATING, fogConfig, mapsConfig, mapRequestQuery);
    }


    @Override
    public FogResponse getResponse(String targetUrl, FogRequest testRequest) {
        return template.postForObject(targetUrl, testRequest, FogResponse.class);
    }

    /**
     * Provides all the routes calculated by google maps. Returns AppException in case of an error.
     * request location.
     *
     * @param mapsConfig      Configuration object of Google Maps
     * @param mapRequestQuery Parametrized url query to fetch fetch directions from google maps
     * @return Direction routes if request is successful.
     */
    private DirectionResult getDirectionRoutes(MapsConfig mapsConfig, String mapRequestQuery) throws AppException {
        DirectionApi directionApi = new DirectionApi(responseHandler);
        DirectionResult result;
        try {
            result = directionApi.getSyncNonRankedDirectionRoutes(mapRequestQuery, mapsConfig);
            LOG.info("Routes has been fetched from Direction API.");
        } catch (NullPointerException ne) {
            LOG.error("Google maps api returned NULL while fetching routes from Direction API.", ne);
            throw new AppException("ROUTE_FETCH_ERROR", "No such route found for the requested source and destination.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new AppException("UNKNOWN_ERROR", "Unknown exception occurred while fetching routes from Direction API.");
        }
        if ("OK".equals(result.getStatus().trim()) || "ZERO_RESULTS".equals(result.getStatus().trim())) {
            return result;
        } else {
            throw new AppException("ZERO_RESULTS", "System could not find routes for the requested source and destination.");
        }
    }

    private FogRequest getRequestDataForFogNode(DirectionRoute[] routes, Pollution pollution) {
        LOG.info("Generating request data for fog node.");
        return pollution.getUniqueCoordinatesFromAllDirections(routes);
    }

    private FogResponse getResponseFromFogNode(String targetUrl, FogRequest request) {
        FogNodeApi api = new FogNodeApi();
        LOG.info("Fetching response from fog node.");
        LOG.info("Fog node target url is {}", targetUrl);
        return api.getFogNodeRouteRatingResponse(targetUrl, request, template);
    }

    private ResponseEntity getRating(RatingType type, FogConfig fogConfig, MapsConfig mapsConfig, String mapRequestQuery) {

        DirectionResult routeResult;
        //Get routes from google maps api
        try {
            routeResult = getDirectionRoutes(mapsConfig, mapRequestQuery);
        } catch (AppException appException) {
            Error error = new Error(appException.getMessage(), appException.getErrorCode());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        Pollution pollution = new Pollution();

        //Generate request to send POST call on fog node.
        FogRequest request = getRequestDataForFogNode(routeResult.getRoutes(), pollution);

        //Read response from fog node
        FogResponse nodeResult = getResponseFromFogNode(fogConfig.getUrl(), request);

        switch (nodeResult.getStatus()) {
            case "OK":
                if (type.equals(RatingType.ALL_RATING)) {
                    List<RouteRating> ratings;
                    //Create Map for routes and its rating
                    Map<DirectionRoute, Integer> routeRatings = pollution.getRouteRatings(routeResult.getRoutes(), nodeResult);
                    ratings = new ArrayList<>();

                    //Sanitize the response from Map object to class object
                    for (Map.Entry<DirectionRoute, Integer> entry : routeRatings.entrySet()) {
                        ratings.add(new RouteRating(entry.getValue(), entry.getKey()));
                    }
                    return new ResponseEntity<>(new RatingList(ratings), HttpStatus.OK);

                } else if (type.equals(RatingType.BEST_RATING)) {
                    return new ResponseEntity<>(pollution.getBestRoute(routeResult.getRoutes(), nodeResult), HttpStatus.OK);

                } else {
                    LOG.error("System has been requested to show results for UNKNOWN request. Request failed.");
                    return new ResponseEntity<>(new Error("Server could not understand the request", "INVALID_REQUEST"), HttpStatus.BAD_REQUEST);
                }
            case "ZERO_RESULTS":
                LOG.error(nodeResult.getErrorMessage());
                return new ResponseEntity<>(new Error("ZERO_RESULTS", "Fog server could not process the request!"), HttpStatus.NOT_FOUND);
            default:
                String errorMessage = "Unknown error occurred encountered while at Fog Server. Status: " + nodeResult.getStatus() +
                        ", Error: " + nodeResult.getErrorMessage();
                LOG.error(errorMessage);
                return new ResponseEntity<>(new Error(errorMessage, "UNKNOWN_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
