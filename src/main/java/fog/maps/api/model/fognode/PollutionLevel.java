package fog.maps.api.model.fognode;

import java.util.Locale;

/**
 * Created by nakulsharma on 24-11-2016.
 * Enumerator for pollution level of a coordinate
 */
public enum PollutionLevel {
    HIGH,
    MEDIUM_HIGH,
    MEDIUM,
    MEDIUM_LOW,
    LOW;

    public String getPollutionLevel(){
        return this.name();
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
