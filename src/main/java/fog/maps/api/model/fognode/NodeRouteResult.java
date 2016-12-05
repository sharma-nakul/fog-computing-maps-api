package fog.maps.api.model.fognode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fog.maps.api.model.direction.Bounds;

/**
 * Created by nakulsharma on 01-12-2016.
 * Mapped model of fognode results and pollution level results
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeRouteResult {

    private String summary;
    private String copyrights;
    private String[] warnings;
    private int[] waypointOrder;
    private Bounds bounds;
    private NodeDirectionLeg[] legs;

    public NodeRouteResult() {
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

    public NodeDirectionLeg[] getLegs() {
        return legs;
    }

    public void setLegs(NodeDirectionLeg[] legs) {
        this.legs = legs;
    }
}
