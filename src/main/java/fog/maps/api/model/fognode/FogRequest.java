package fog.maps.api.model.fognode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fog.maps.api.model.direction.Coordinate;

import java.util.ArrayList;

/**
 * Created by nakulsharma on 23-11-2016.
 * Unique coordinates of all fognode for a source-destination.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FogRequest {

    /**
     * List if coordinates for all the fognode of a direction
     */
    private ArrayList<Coordinate> coordinates;

    /**
     * Default constructor
     */
    public FogRequest() {
    }

    /**
     * Constructor to set coordinates of fognode
     * @param coordinates
     */
    public FogRequest(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
