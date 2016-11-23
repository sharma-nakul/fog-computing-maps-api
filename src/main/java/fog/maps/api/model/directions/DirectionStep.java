package fog.maps.api.model.directions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nakulsharma on 22-11-2016.
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionStep {

    private Distance distance;
    private Duration duration;
    private Coordinates startLocation;
    private Coordinates endLocation;
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

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public TravelMode getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }
}
