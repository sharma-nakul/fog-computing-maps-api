package fog.maps.api.model.route;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 24-11-2016.
 * Coordinates and pollution level model of IoT device
 */
public class FogNodeCoordinate {

    private double lat;
    private double lng;

    @JsonProperty("pollution_level")
    private PollutionLevel pollutionLevel;

    /**
     * Default Constructor
     */
    public FogNodeCoordinate() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public PollutionLevel getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(PollutionLevel pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }
}