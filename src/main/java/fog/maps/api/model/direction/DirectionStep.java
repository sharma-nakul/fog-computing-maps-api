package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 22-11-2016.
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionStep {

    private Distance distance;
    private Duration duration;
    private Coordinate startLocation;
    private Coordinate endLocation;
    private String htmlInstructions;
    private TravelMode travelMode;

    //todo: EncodedPolyline polyline object is not set here. Set it if required.

    /**
     * Default Constructor
     */
    public DirectionStep() {
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

    @JsonProperty("html_instructions")
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    @JsonProperty("travel_mode")
    public TravelMode getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }
}
