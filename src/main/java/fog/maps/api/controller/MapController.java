package fog.maps.api.controller;

import fog.maps.api.internal.FogConfig;
import fog.maps.api.internal.MapsConfig;
import fog.maps.api.service.IApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nakulsharma on 02-12-2016.
 * Controller for google maps services
 */

@RestController
public class MapController {

    private static final MapsConfig MAPS_CONFIG = new MapsConfig("/maps/api/directions/json");
    //todo: Give hostName received from Deep below
    private static final FogConfig FOG_CONFIG = new FogConfig("");

    @Autowired
    IApiService apiService;


    @RequestMapping(method = RequestMethod.GET, value = "/best_route")
    public ResponseEntity getBestRoute() {
        //todo: put fog node and maps query
        String fogQuery = "";
        String mapsQuery = "?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
        return apiService.getBestRoute(FOG_CONFIG, fogQuery, MAPS_CONFIG, mapsQuery);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/route_ratings")
    public ResponseEntity getRouteRatings() {
        String mapsQuery = "?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
        String fogQuery = "";
        return apiService.getRouteRatings(FOG_CONFIG, fogQuery, MAPS_CONFIG, mapsQuery);
    }

}
