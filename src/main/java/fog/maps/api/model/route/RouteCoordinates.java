package fog.maps.api.model.route;

import com.fasterxml.jackson.annotation.JsonInclude;
import fog.maps.api.model.direction.Coordinate;

import java.util.ArrayList;

/**
 * Created by nakulsharma on 23-11-2016.
 * Unique coordinates of all route for a source-destination.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteCoordinates {

    /**
     * List if coordinates for all the route of a direction
     */
    private ArrayList<Coordinate> coordinates;

    /**
     * Http status like OK, NOT_FOUND, BAD_REQUEST, etc.
     */
    private String status;

    /**
     * If status is other than OK, then this field will have description of a status.
     * This field will not visible if status is OK
     */
    private String error_message;

    /**
     * Default constructor
     */
    public RouteCoordinates() {
    }

    /**
     * Constructor to set all the fields/properties
     * @param coordinates List if coordinates for all the route of a direction.
     * @param status Http status like OK, NOT_FOUND, BAD_REQUEST, etc.
     * @param error_message Description of an error if status is not OK
     */
    public RouteCoordinates(ArrayList<Coordinate> coordinates, String status, String error_message) {
        this.coordinates = coordinates;
        this.status = status;
        this.error_message = error_message;
    }

    /**
     * Constructor to set coordinates of route
     * @param coordinates
     */
    public RouteCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
