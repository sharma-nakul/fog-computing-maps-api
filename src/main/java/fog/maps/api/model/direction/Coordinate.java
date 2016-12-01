package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by nakulsharma on 22-11-2016.
 * Place on Earth, represented by latitude and longitudes a.k.a coordinates
 */
public class Coordinate {
    private double lat;
    private double lng;

    /**
     * Default Constructor
     */
    public Coordinate() {
    }

    public Coordinate(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
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

    @JsonIgnore
    public String getCoordinates()
    {
        return lat+","+lng;
    }
}
