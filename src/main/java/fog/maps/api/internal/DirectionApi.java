package fog.maps.api.internal;

import fog.maps.api.model.Error;
import fog.maps.api.model.direction.DirectionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by nakulsharma on 01-12-2016.
 * Represents the complete response of Google Maps Directions API
 */

@Component
public class DirectionApi {

    private static Logger LOG = LoggerFactory.getLogger(DirectionApi.class);

    private final ResponseHandler responseHandler;

    @Autowired
    public DirectionApi(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public DirectionResult getSyncNonRankedDirectionRoutes(String requestQuery, MapsConfig config) {
        Map<Class<?>, ParameterizedTypeReference> typeReferences = new HashMap<>();
        typeReferences.put(DirectionResult.class, new ParameterizedTypeReference<DirectionResult>() {
        });

        try {
            ResponseEntity<DirectionResult> responseEntity = responseHandler.synchronousGet(config, requestQuery, DirectionResult.class, typeReferences);
            return responseEntity.getBody();
        } catch (Exception e) {
            LOG.error("Exception occurred while fetching directions from google maps in a synchronously. {}", e.getMessage());
            return null;
        }
    }

    public DirectionResult getAsyncNonRankedDirectionRoutes(String requestQuery, MapsConfig config) {
        Map<Class<?>, ParameterizedTypeReference> typeReferences = new HashMap<>();
        typeReferences.put(DirectionResult.class, new ParameterizedTypeReference<DirectionResult>() {
        });

        try {
            Future<ResponseEntity<DirectionResult>> response = responseHandler.asyncGet(config, requestQuery, DirectionResult.class, typeReferences);
            return response.get().getBody();
        } catch (Exception e) {
            LOG.error("Exception occurred while performing asynchronous fetch of non-ranked directions. {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
