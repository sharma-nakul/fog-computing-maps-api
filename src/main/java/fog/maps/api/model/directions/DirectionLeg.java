package fog.maps.api.model.directions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nakulsharma on 22-11-2016.
 * It is a component of Direction API Result
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionLeg {

    private Distance distance;
    private Duration duration;
    private String startAddress;
    private String endAddress;
    private Coordinates startLocation;
    private Coordinates endLocation;
    private DirectionStep[] steps;

    //todo: DateTime arrivalTime and departureTime is available only in case of public transport. Use it, if needed
    //todo: Array of traffic_speed_entry is not defined here and it is a part of JSON response. Identify its data type
    //todo: Array of via_waypoint is not defined here and it is a part of JSON response. Identify its data type


    /**
     * Default Constructor
     */
    public DirectionLeg() {
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Coordinates getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Coordinates startLocation) {
        this.startLocation = startLocation;
    }

    public Coordinates getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Coordinates endLocation) {
        this.endLocation = endLocation;
    }

    public DirectionStep[] getSteps() {
        return steps;
    }

    public void setSteps(DirectionStep[] steps) {
        this.steps = steps;
    }

}
