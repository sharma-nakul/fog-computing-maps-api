package fog.maps.api.service;

import fog.maps.api.internal.FogConfig;
import fog.maps.api.internal.MapsConfig;
import org.springframework.http.ResponseEntity;

/**
 * Created by nakulsharma on 22-11-2016.
 * Interface that defines methods to perform air quality check on fog nodes
 */

public interface IApiService {

    ResponseEntity getBestRoute(FogConfig config, String fogRequestQuery, MapsConfig mapsConfig, String mapRequestQuery);

    ResponseEntity getRouteRatings(FogConfig config, String fogRequestQuery, MapsConfig mapsConfig, String mapRequestQuery);

    ResponseEntity getDirectionRoutes(MapsConfig mapsConfig, String mapRequestQuery);
}
