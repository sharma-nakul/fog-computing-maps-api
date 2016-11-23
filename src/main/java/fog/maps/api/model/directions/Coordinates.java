package fog.maps.api.model.directions;

import java.io.Serializable;

/**
 * Created by nakulsharma on 22-11-2016.
 * Place on Earth, represented by latitude and longitudes a.k.a coordinates
 */
public class Coordinates{
    private double lat;
    private double lng;

    /**
     * Default Constructor
     */
    public Coordinates() {
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
}
