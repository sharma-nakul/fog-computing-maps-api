package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private Coordinate startLocation;
    private Coordinate endLocation;
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

    @JsonProperty("start_address")
    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    @JsonProperty("end_address")
    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    @JsonProperty("start_location")
    public Coordinate getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Coordinate startLocation) {
        this.startLocation = startLocation;
    }

    @JsonProperty("end_location")
    public Coordinate getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Coordinate endLocation) {
        this.endLocation = endLocation;
    }

    public DirectionStep[] getSteps() {
        return steps;
    }

    public void setSteps(DirectionStep[] steps) {
        this.steps = steps;
    }

}
