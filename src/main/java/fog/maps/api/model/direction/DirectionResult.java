package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 22-11-2016.
 * DirectionResult represents the result from Google Maps Direction API web internal.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionResult {

    private String status;

    @JsonProperty("error_message")
    private String errorMessage;

    private DirectionRoute[] routes;

    public DirectionResult() {
    }

    //todo: fix Geocoded waypoints - it is throwing error at ADDRESS_TYPE field of its model.
    //private GeocodedWaypoint[] geocodedWaypoints;


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
}
