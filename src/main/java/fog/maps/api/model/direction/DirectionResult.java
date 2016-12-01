package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nakulsharma on 22-11-2016.
 * DirectionResult represents the result from Google Maps Direction API web logic.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionResult {
    private DirectionRoute[] routes;
    //private GeocodedWaypoint[] geocodedWaypoints;
    private String status;
    //private String errorMessage;


    public DirectionResult() {
    }

    public DirectionRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(DirectionRoute[] routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
