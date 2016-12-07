package fog.maps.api.model.fognode;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 24-11-2016.
 * Coordinates and pollution level model of IoT device
 */
public class NodeCoordinate {

    private double lat;
    private double lng;

    @JsonProperty("pollution_level")
    private Integer pollutionLevel;

    /**
     * Default Constructor
     */
    public NodeCoordinate() {
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

    public Integer getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(Integer pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }
}