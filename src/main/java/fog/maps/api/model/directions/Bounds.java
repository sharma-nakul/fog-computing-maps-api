package fog.maps.api.model.directions;

import java.io.Serializable;

/**
 * Created by nakulsharma on 22-11-2016.
 * Bounds contains the viewport bounding box of the overview_polyline
 */
public class Bounds{
    private Coordinates northeast;
    private Coordinates southwest;

    /**
     * Default Constructor
     */
    public Bounds() {
    }

    public Coordinates getNortheast() {
        return northeast;
    }

    public void setNortheast(Coordinates northeast) {
        this.northeast = northeast;
    }

    public Coordinates getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Coordinates southwest) {
        this.southwest = southwest;
    }
}
