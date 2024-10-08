package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 22-11-2016.
 * A Directions API result. When the Directions API returns results, it places them within a fognode
 * array. Even if the internal returns no results (such as if the origin and/or destination doesn't
 * exist) it still returns an empty fognode array.
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

    @JsonProperty("waypoint_order")
    public int[] getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(int[] waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public DirectionLeg[] getLegs() {
        return legs;
    }

    public void setLegs(DirectionLeg[] legs) {
        this.legs = legs;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }


}
