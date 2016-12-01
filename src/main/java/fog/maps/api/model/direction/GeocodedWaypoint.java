package fog.maps.api.model.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 22-11-2016.
 * Geocoded Waypoint represents a point in a Directions API response, either the origin, one of the
 * requested waypoints, or the destination.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodedWaypoint {

    private String geocoderStatus;
    private boolean partialMatch;
    private String placeId;
    private AddressType[] types;

    /**
     * Default constructor
     */
    public GeocodedWaypoint() {
    }

    @JsonProperty("geocoder_status")
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    @JsonProperty("partial_match")
    public boolean isPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }

    @JsonProperty("place_id")
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public AddressType[] getTypes() {
        return types;
    }

    public void setTypes(AddressType[] types) {
        this.types = types;
    }
}
