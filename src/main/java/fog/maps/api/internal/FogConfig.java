package fog.maps.api.internal;

/**
 * Created by nakulsharma on 04-12-2016.
 * Configuration class of a fog node
 */

public class FogConfig {
    public String path;
    //todo: put hostName given by DEEP
    public String hostName = "";
    public String requestVerb = "GET";

    public FogConfig(String path) {
        this.path = path;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setRequestVerb(String requestVerb) {
        this.requestVerb = requestVerb;
    }
}
