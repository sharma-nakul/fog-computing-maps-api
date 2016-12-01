package fog.maps.api.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fog.maps.api.error.ApiExceptions;
import fog.maps.api.model.direction.DirectionResult;
import fog.maps.api.model.direction.DirectionRoute;
import org.springframework.stereotype.Component;

/**
 * Created by nakulsharma on 01-12-2016.
 * Represents the complete response of Google Maps Directions API
 */

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionApiResponse implements ApiResponse<DirectionResult> {

    private String status;
    private String errorMessage;
    private DirectionRoute[] routes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DirectionRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(DirectionRoute[] routes) {
        this.routes = routes;
    }

    /**
     * Provides the status of request from direction api
     *
     * @return
     */
    @Override
    public boolean success() {
        return "OK".equals(status) || "ZERO_RESULTS".equals(status);
    }

    /**
     * Represents the response from direction api
     *
     * @return Directions from origin to destination
     */
    @Override
    public DirectionResult getResponse() {
        DirectionResult result = new DirectionResult();
        result.setRoutes(routes);
        return result;
    }

    /**
     * Provide API exception of status received from directions api. Returns null if status is success.
     *
     * @return Valid API exception
     */
    @Override
    public ApiExceptions getError() {
        if (success()) {
            return null;
        }
        return ApiExceptions.get(status, errorMessage);
    }
}
