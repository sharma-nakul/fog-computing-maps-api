package fog.maps.api.controller;

import fog.maps.api.internal.FogConfig;
import fog.maps.api.internal.MapsConfig;
import fog.maps.api.service.IApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static Logger LOG = LoggerFactory.getLogger(MapController.class);

    private static final MapsConfig MAPS_CONFIG = new MapsConfig("/maps/api/directions/json");
    //todo: Give hostName received from Deep below
    private static final FogConfig FOG_CONFIG = new FogConfig("");

    @Autowired
    IApiService apiService;


 @RequestMapping(method = RequestMethod.GET, value = "/best_route")
    public ResponseEntity getBestRoute() {
        try {
            //todo: put fog node and maps query
            String fogQuery="";
            String mapsQuery="?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
            ResponseEntity response=apiService.getBestRoute(FOG_CONFIG,fogQuery,MAPS_CONFIG,mapsQuery);
            HttpStatus responseCode=response.getStatusCode();
            if(responseCode==HttpStatus.FOUND)
            {
                return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Invalid request",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            LOG.error("Internal Server Error" + e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_ratings")
    public ResponseEntity getRouteRatings() {
        try {

            String mapsQuery="?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
            String fogQuery="";
            ResponseEntity response=apiService.getRouteRatings(FOG_CONFIG,fogQuery,MAPS_CONFIG,mapsQuery);
            HttpStatus responseCode=response.getStatusCode();
            if(responseCode==HttpStatus.FOUND)
            {
                return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Invalid request",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            LOG.error("Internal Server Error" + e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "get_directions")
    public ResponseEntity getDirectionResult(){
        String mapsQuery="?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
        ResponseEntity response=apiService.getDirectionRoutes(MAPS_CONFIG,mapsQuery);
        return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
    }
}
