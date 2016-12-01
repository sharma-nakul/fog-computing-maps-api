package fog.maps.api.model.test;

import fog.maps.api.model.route.FogNodeStatusCode;

import java.util.List;

/**
 * Created by nakulsharma on 29-11-2016.
 * Temporary class to create JSON
 */
public class JsonRequestObject {

    /**
     * List if coordinates with pollution level for all the route of a direction
     */
    private List<JsonRequestCoordinate> coordinates;

    /**
     * Response status
     */
    private FogNodeStatusCode status;

    public JsonRequestObject() {
    }

    /**
     * Default constructor
     */

    public List<JsonRequestCoordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<JsonRequestCoordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public FogNodeStatusCode getStatus() {
        return status;
    }

    public void setStatus(FogNodeStatusCode status) {
        this.status = status;
    }
}
