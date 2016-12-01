package fog.maps.api.logic;

/**
 * Created by nakulsharma on 29-11-2016.
 * Configures the fields in Google Maps API
 */
public class MapsConfig {

    public String path;
    public String hostName="https://maps.googleapis.com";
    public String requestVerb="GET";
    public String supportClientId="false";

    public MapsConfig(String path){
        this.path=path;
    }

    public MapsConfig hostName(String hostName){
        this.hostName=hostName;
        return this;
    }

    public MapsConfig requestVerb(String requestVerb){
        this.requestVerb=requestVerb;
        return this;
    }
}
