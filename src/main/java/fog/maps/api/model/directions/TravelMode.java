package fog.maps.api.model.directions;

import java.util.Locale;

/**
 * Created by nakulsharma on 22-11-2016.
 * Enumerator for Direction API travel mode
 */
public enum TravelMode {
    DRIVING,
    WALKING,
    BICYCLING,
    UNKNOWN;

    //Todo: Travel Mode - "TRANSIT" is not added. Add if required.

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
