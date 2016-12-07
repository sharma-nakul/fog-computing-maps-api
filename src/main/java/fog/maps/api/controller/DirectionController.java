package fog.maps.api.controller;

import fog.maps.api.model.direction.DirectionResult;
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

    private String requestUrl;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/directions")
    public ResponseEntity getMapDirections() {
        try {
            requestUrl = "https://maps.googleapis.com/maps/api/directions/json?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
            //requestUrl="https://maps.googleapis.com/maps/api/directions/json?origin=101+E+San+Fernando+Street+San+Jose&destination=costco+wholesale+2201+senter+road+San+jose&alternatives=true&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
            DirectionResult direction = restTemplate.getForObject(requestUrl, DirectionResult.class);
            return new ResponseEntity<>(direction, HttpStatus.OK);
        } catch (IllegalStateException e) {
            LOG.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
