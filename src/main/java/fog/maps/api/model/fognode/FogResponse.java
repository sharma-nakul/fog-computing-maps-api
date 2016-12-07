package fog.maps.api.model.fognode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 24-11-2016.
 * Reads the JSON response from IoT application
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FogResponse {

    /**
     * List if coordinates with pollution level for all the fognode of a direction
     */
    private NodeCoordinate[] coordinates;

    private String status;

    @JsonProperty("error_message")
    private String errorMessage;

    /**
     * Default constructor
     */
    public FogResponse() {
    }

    public NodeCoordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(NodeCoordinate[] coordinates) {
        this.coordinates = coordinates;
    }

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
}
