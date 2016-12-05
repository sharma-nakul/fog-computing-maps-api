package fog.maps.api.model.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import fog.maps.api.model.fognode.PollutionLevel;

/**
 * Created by nakulsharma on 29-11-2016.
 * Temporary class to create JSON of coordinates.
 */
public class JsonRequestCoordinate {
    private double lat;
    private double lng;

    @JsonProperty("pollution_level")
    private PollutionLevel pollutionLevel;

    public JsonRequestCoordinate() {
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
