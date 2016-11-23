package fog.maps.api.model.directions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nakulsharma on 22-11-2016.
 * A Directions API result. When the Directions API returns results, it places them within a routes
 * array. Even if the service returns no results (such as if the origin and/or destination doesn't
 * exist) it still returns an empty routes array.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionRoute {
    private String summary;
    private String copyrights;
    private String[] warnings;
    private int[] waypointOrder;
    private Bounds bounds;
    private DirectionLeg[] legs;

    //todo: EncodedPolyline polyline object is not set here. Set it if required.

    /**
     * Default Constructor
     */
    public DirectionRoute() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public String[] getWarnings() {
        return warnings;
    }

    public void setWarnings(String[] warnings) {
        this.warnings = warnings;
    }

    public int[] getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(int[] waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }


}
