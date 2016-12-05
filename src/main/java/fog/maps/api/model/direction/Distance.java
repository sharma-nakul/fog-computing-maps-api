package fog.maps.api.model.direction;

/**
 * Created by nakulsharma on 22-11-2016.
 * distance indicates the total distance covered by this leg.
 * These fields may be absent if the distance is unknown.
 */
public class Distance {

    /**
     * text contains a human-readable representation of the distance, displayed in units as used at the origin (or as
     * overridden within the units parameter in the request). (For example, miles and feet will be used for any origin
     * within the United States.) Note that regardless of what unit system is displayed as text, the distance.value
     * field always contains a value expressed in meters.
     */
    private String text;

    /**
     * value indicates distance in meters
     */
    private String value;

    /**
     * Default Constructor
     */
    public Distance() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
