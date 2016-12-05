package fog.maps.api.model.fognode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fog.maps.api.model.direction.DirectionStep;
import fog.maps.api.model.direction.Distance;
import fog.maps.api.model.direction.Duration;

/**
 * Created by nakulsharma on 01-12-2016.
 * Mapped results of direction leg of maps and pollution sensor
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeDirectionLeg {

    private Distance distance;
    private Duration duration;
    private String startAddress;
    private String endAddress;
    private NodeCoordinate startLocation;
    private NodeCoordinate endLocation;
    private DirectionStep[] steps;
}
