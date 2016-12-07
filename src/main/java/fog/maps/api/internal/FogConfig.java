package fog.maps.api.internal;

/**
 * Created by nakulsharma on 04-12-2016.
 * Configuration class of a fog node
 */

public class FogConfig {
    public String path;
    public String hostName = "http://ec2-35-165-60-184.us-west-2.compute.amazonaws.com:8000";

    public FogConfig(String path) {
        this.path = path;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUrl(){
        return hostName+path;
    }
}
