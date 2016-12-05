package fog.maps.api.service;

import fog.maps.api.internal.*;
import fog.maps.api.model.Error;
import fog.maps.api.model.direction.DirectionResult;
import fog.maps.api.model.fognode.NodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
    public ResponseEntity getBestRoute(FogConfig config, String fogRequestQuery, MapsConfig mapsConfig, String mapRequestQuery) {
        DirectionApi directionApi = new DirectionApi(responseHandler);
        try {
            DirectionResult result = directionApi.getSyncNonRankedDirectionRoutes(mapRequestQuery, mapsConfig);
            if (result == null)
                return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
            else {
                if (!result.getStatus().equals("OK") || !result.getStatus().equals("ZERO_RESULTS"))
                    return new ResponseEntity<>("Zero routes found", HttpStatus.NO_CONTENT);
                else {
                    LOG.info(Arrays.toString(result.getRoutes()));
                    FogNodeApi fogNodeApi = new FogNodeApi();
                    //todo: Replace getResponseFromFogNode with Uri given by Deep
                    //NodeResult nodeResult = fogNodeApi.getResponseFromFogNode(config,fogRequestQuery, template);
                    String filePath = "C:\\Users\\nakulsharma\\Documents\\295B - Project\\fog.maps.api\\src\\main\\resources\\iot.json";
                    NodeResult nodeResult = fogNodeApi.getFogNodeResponseFromFile(filePath);
                    Pollution pollution = new Pollution();
                    return new ResponseEntity<>(pollution.getBestRoute(result.getRoutes(), nodeResult), HttpStatus.FOUND);
                }
            }
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOG.error("Error fetching direction results. {}", e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public ResponseEntity getRouteRatings(FogConfig config, String fogRequestQuery, MapsConfig mapsConfig, String mapRequestQuery) {
        DirectionApi directionApi = new DirectionApi(responseHandler);
        try {

            DirectionResult result = directionApi.getSyncNonRankedDirectionRoutes(mapRequestQuery, mapsConfig);
            if (result == null)
                return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
            else {
                if (!result.getStatus().equals("OK") || !result.getStatus().equals("ZERO_RESULTS"))
                    return new ResponseEntity<>("Zero routes found", HttpStatus.NO_CONTENT);
                else {
                    FogNodeApi fogNodeApi = new FogNodeApi();
                    //todo: Replace getResponseFromFogNode with Uri given by Deep
                    //NodeResult nodeResult = fogNodeApi.getResponseFromFogNode(config,fogRequestQuery, template);
                    String filePath = "C:\\Users\\nakulsharma\\Documents\\295B - Project\\fog.maps.api\\src\\main\\resources\\iot.json";
                    NodeResult nodeResult = fogNodeApi.getFogNodeResponseFromFile(filePath);
                    Pollution pollution = new Pollution();
                    return new ResponseEntity<>(pollution.getRouteRatings(result.getRoutes(), nodeResult), HttpStatus.FOUND);
                }
            }

           /* ResponseEntity directionResults = directionApi.getSyncNonRankedDirectionRoutes(mapRequestQuery, mapsConfig);
            if (directionResults.getStatusCode() == HttpStatus.OK) {
                DirectionResult result = (DirectionResult) directionResults.getBody();
                if (!result.getStatus().equals("OK") || !result.getStatus().equals("ZERO_RESULTS")) {
                    return new ResponseEntity<>("Zero results found", HttpStatus.NO_CONTENT);
                } else {
                    FogNodeApi fogNodeApi = new FogNodeApi();
                    //NodeResult nodeResult = fogNodeApi.getResponseFromFogNode(config,fogRequestQuery, template);
                    String filePath = "C:\\Users\\nakulsharma\\Documents\\295B - Project\\fog.maps.api\\src\\main\\resources\\iot.json";
                    NodeResult nodeResult = fogNodeApi.getFogNodeResponseFromFile(filePath);
                    Pollution pollution = new Pollution();
                    return new ResponseEntity<>(pollution.getRouteRatings(result.getRoutes(), nodeResult), HttpStatus.FOUND);
                }
            } else {
                String message = "Request failed to fetch data from Google maps direction API";
                LOG.error(message);
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }*/
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOG.error("Error fetching direction results. {}", e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public ResponseEntity getDirectionRoutes(MapsConfig mapsConfig, String mapRequestQuery) {
        try {
            DirectionApi directionApi = new DirectionApi(responseHandler);
            DirectionResult result = directionApi.getSyncNonRankedDirectionRoutes(mapRequestQuery, mapsConfig);
            if (result == null)
                return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
            else {
                if ("OK".equals(result.getStatus().trim()) || "ZERO_RESULTS".equals(result.getStatus().trim()))
                    return new ResponseEntity<>(result.getRoutes(), HttpStatus.FOUND);
                else {
                    Error error = new Error("System could not find routes for the requested the direction", "ZERO_RESULTS");
                    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }
}
