package fog.maps.api.model.fognode;

/**
 * Created by nakulsharma on 24-11-2016.
 * Reads the JSON response from IoT application
 */
public class NodeResult {

    /**
     * List if coordinates with pollution level for all the fognode of a direction
     */
    private NodeCoordinate[] coordinates;

    /**
     * Default constructor
     */
    public NodeResult() {
    }

    public NodeCoordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(NodeCoordinate[] coordinates) {
        this.coordinates = coordinates;
    }
}
