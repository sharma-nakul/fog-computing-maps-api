package fog.maps.api.model.direction;

/**
 * Created by nakulsharma on 22-11-2016.
 * Bounds contains the viewport bounding box of the overview_polyline
 */
class Bounds{
    private Coordinate northeast;
    private Coordinate southwest;

    /**
     * Default Constructor
     */
    public Bounds() {
    }

    public Coordinate getNortheast() {
        return northeast;
    }

    public void setNortheast(Coordinate northeast) {
        this.northeast = northeast;
    }

    public Coordinate getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Coordinate southwest) {
        this.southwest = southwest;
    }
}
