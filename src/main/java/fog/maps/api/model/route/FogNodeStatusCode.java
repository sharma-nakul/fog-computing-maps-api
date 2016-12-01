package fog.maps.api.model.route;

/**
 * Created by nakulsharma on 24-11-2016.
 * Enumerator of acceptable response code from IoT device
 */
public enum FogNodeStatusCode {
    OK,
    NOT_FOUND,
    ZERO_RESULTS,
    INVALID_REQUEST,
    REQUEST_DENIED,
    UNKNOWN_ERROR;

    public String getPollutionLevel(){
        return this.name();
    }
}
