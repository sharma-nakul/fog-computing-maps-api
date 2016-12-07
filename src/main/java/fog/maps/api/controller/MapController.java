package fog.maps.api.controller;

import fog.maps.api.internal.FogConfig;
import fog.maps.api.internal.MapsConfig;
import fog.maps.api.model.direction.Coordinate;
import fog.maps.api.model.fognode.FogRequest;
import fog.maps.api.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by nakulsharma on 02-12-2016.
 * Controller for google maps services
 */

@RestController
public class MapController {

    private static final MapsConfig MAPS_CONFIG = new MapsConfig("/maps/api/directions/json");
    private static final FogConfig FOG_CONFIG = new FogConfig("/web/getRecords");

    @Autowired
    private IApiService apiService;


    @RequestMapping(method = RequestMethod.GET, value = "/best_route")
    public ResponseEntity getBestRoute() {
        String mapsQuery = "?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
        return apiService.getBestRoute(FOG_CONFIG, MAPS_CONFIG, mapsQuery);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/route_ratings")
    public ResponseEntity getRouteRatings() {
        String mapsQuery = "?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
        return apiService.getRouteRatings(FOG_CONFIG, MAPS_CONFIG, mapsQuery);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test_fog")
    public ResponseEntity getTest() {
        ArrayList<Coordinate> coordinates=new ArrayList<>();
        coordinates.add(new Coordinate(37.3301083 ,-121.8774402 ));
        coordinates.add(new Coordinate(37.3385077 ,-121.8804459 ));
        FogRequest request=new FogRequest(coordinates);
        return new ResponseEntity<>(apiService.getResponse(FOG_CONFIG.getUrl(),request), HttpStatus.OK);
    }
}
