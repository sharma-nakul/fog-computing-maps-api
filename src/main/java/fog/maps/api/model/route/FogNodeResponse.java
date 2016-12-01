package fog.maps.api.model.route;

import java.util.List;

/**
 * Created by nakulsharma on 24-11-2016.
 * Reads the JSON response from IoT application
 */
public class FogNodeResponse {

    /**
     * List if coordinates with pollution level for all the route of a direction
     */
    private List<FogNodeCoordinate> coordinates;

    /**
     * Response status
     */
    private FogNodeStatusCode status;

    /**
     * Default constructor
     */
    public FogNodeResponse() {
    }

    public List<FogNodeCoordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<FogNodeCoordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public FogNodeStatusCode getStatus() {
        return status;
    }

    public void setStatus(FogNodeStatusCode status) {
        this.status = status;
    }
}
