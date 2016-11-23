package fog.maps.api.model.directions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nakulsharma on 22-11-2016.
 * DirectionResult represents the result from Google Maps Direction API web service.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionResult {
    private DirectionRoute[] routes;
    private GeocodedWaypoint[] geocodedWaypoints;
    private String status;
    //todo: Add additional field for error_message in case status is not OK.


    public DirectionResult() {
    }

    public DirectionRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(DirectionRoute[] routes) {
        this.routes = routes;
    }

    public GeocodedWaypoint[] getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    public void setGeocodedWaypoints(GeocodedWaypoint[] geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
