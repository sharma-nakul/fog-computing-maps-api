package fog.maps.api.model.direction;

/**
 * Created by nakulsharma on 22-11-2016.
 * duration indicates the total duration of this leg, as a field with the following elements.
 * These fields may be absent if the duration is unknown.
 */
public class Duration {

    /**
     * text contains a human-readable representation of the duration.
     */
    private String text;

    /**
     * value indicates the duration in seconds.
     */
    private  String value;

    /**
     * Default Constructor
     */
    public Duration() {
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

    @Override
    public String toString() {
        return "Duration{" +
                "text='" + text + '\'' +
                '}';
    }
}
