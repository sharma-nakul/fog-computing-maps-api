package fog.maps.api.service;

import fog.maps.api.error.AppException;
import fog.maps.api.internal.*;
import fog.maps.api.model.Error;
import fog.maps.api.model.direction.DirectionResult;
import fog.maps.api.model.direction.DirectionRoute;
import fog.maps.api.model.fognode.NodeResult;
import fog.maps.api.model.fognode.RatingList;
import fog.maps.api.model.fognode.RouteRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseEntity getBestRoute(FogConfig config, String fogRequestQuery,
                                       MapsConfig mapsConfig, String mapRequestQuery) {
        DirectionResult routeResult;
        try {
            routeResult = getDirectionRoutes(mapsConfig, mapRequestQuery);
        } catch (AppException appException) {
            Error error = new Error(appException.getMessage(), appException.getErrorCode());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        FogNodeApi fogNodeApi = new FogNodeApi();
        //todo: Replace getResponseFromFogNode with Uri given by Deep
        //NodeResult nodeResult = fogNodeApi.getResponseFromFogNode(config,fogRequestQuery, template);
        String filePath = "C:\\Users\\nakulsharma\\Documents\\295B - Project\\fog.maps.api\\src\\main\\resources\\iot.json";
        NodeResult nodeResult = fogNodeApi.getFogNodeResponseFromFile(filePath);
        DirectionRoute route = new Pollution().getBestRoute(routeResult.getRoutes(), nodeResult);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getRouteRatings(FogConfig config, String fogRequestQuery,
                                          MapsConfig mapsConfig, String mapRequestQuery) {

        DirectionResult routeResult;
        try {
            routeResult = getDirectionRoutes(mapsConfig, mapRequestQuery);
        } catch (AppException appException) {
            Error error = new Error(appException.getMessage(), appException.getErrorCode());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        List<RouteRating> ratings;
        FogNodeApi fogNodeApi = new FogNodeApi();
        //todo: Replace getResponseFromFogNode with Uri given by Deep
        //NodeResult nodeResult = fogNodeApi.getResponseFromFogNode(config,fogRequestQuery, template);
        String filePath = "C:\\Users\\nakulsharma\\Documents\\295B - Project\\fog.maps.api\\src\\main\\resources\\iot.json";
        NodeResult nodeResult = fogNodeApi.getFogNodeResponseFromFile(filePath);
        Map<DirectionRoute, Integer> routeRatings = new Pollution().getRouteRatings(routeResult.getRoutes(), nodeResult);
        ratings = new ArrayList<>();
        for (Map.Entry<DirectionRoute, Integer> entry : routeRatings.entrySet()) {
            ratings.add(new RouteRating(entry.getValue(), entry.getKey()));
        }
        return new ResponseEntity<>(new RatingList(ratings), HttpStatus.OK);
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
}
