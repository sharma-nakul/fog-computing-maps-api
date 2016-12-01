package fog.maps.api.service;
import fog.maps.api.model.direction.DirectionRoute;
import fog.maps.api.model.route.FogNodeResponse;
import fog.maps.api.model.route.RouteCoordinates;

/**
 * Created by nakulsharma on 22-11-2016.
 * Interface that defines methods to perform air quality check on fog nodes
 */

public interface IMaps {

    /**
     * It returns object of all the uniques coordinates from all available routes of a map direction
     * @param routes List of available routes of a map direction (from source to destination)
     * @return Object of an unique coordinates
     */
    RouteCoordinates getUniqueCoordinatesFromAllDirections(DirectionRoute[] routes);

    FogNodeResponse getResponseFromFogNode();

}
