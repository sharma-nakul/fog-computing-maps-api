package fog.maps.api.controller;

import fog.maps.api.service.IFogNode;
import fog.maps.api.model.direction.*;
import fog.maps.api.model.route.FogNodeResponse;
import fog.maps.api.model.route.RouteCoordinates;
import fog.maps.api.service.IMaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nakulsharma on 22-11-2016.
 * Controller class for Google Maps Direction Api
 */

@RestController
public class DirectionController {
    private static final Logger LOG = LoggerFactory.getLogger(DirectionController.class);

    String requestUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IMaps pollutionCheck;

    @Autowired
    IFogNode fogNode;

    DirectionController() {
        this.requestUrl = "https://maps.googleapis.com/maps/api/directions/json?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/directions")
    public ResponseEntity getMapDirections() {
        try {
            DirectionResult direction = restTemplate.getForObject(requestUrl, DirectionResult.class);
            return new ResponseEntity<>(direction, HttpStatus.OK);
        } catch (IllegalStateException e) {
            LOG.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/direction/waypoint/all")
    public ResponseEntity getTestDirections() {
        try {
            DirectionResult direction = restTemplate.getForObject(requestUrl, DirectionResult.class);
            RouteCoordinates waypoints = pollutionCheck.getUniqueCoordinatesFromAllDirections(direction.getRoutes());
            //todo: Put enumerator for status codes.
            if (waypoints != null)
                waypoints.setStatus("OK");
            else
                waypoints.setStatus("BAD_REQUEST");

            // todo: Here, send the waypoint data to deep's api and get response. Use that response to map actual results.

            return new ResponseEntity<>(waypoints, HttpStatus.OK);
        } catch (IllegalStateException e) {
            LOG.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fog/fetch_response")
    public ResponseEntity getFogNodeResponse() {
        try {
            FogNodeResponse response = pollutionCheck.getResponseFromFogNode();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalStateException e) {
            LOG.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
