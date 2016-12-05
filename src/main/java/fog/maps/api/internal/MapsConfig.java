package fog.maps.api.internal;

/**
 * Created by nakulsharma on 29-11-2016.
 * Configures the fields in Google Maps API
 */
public class MapsConfig {

    public String path;
    public String hostName = "https://maps.googleapis.com";
    public String requestVerb = "GET";

    public MapsConfig(String path) {
        this.path = path;
    }

    public MapsConfig hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public MapsConfig requestVerb(String requestVerb) {
        this.requestVerb = requestVerb;
        return this;
    }
}
