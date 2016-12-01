package fog.maps.api.controller;

import fog.maps.api.service.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by nakulsharma on 30-11-2016.
 * Temporary controller class to test methods.
 */

@RestController
public class MethodTestController {

    private static final Logger LOG = LoggerFactory.getLogger(MethodTestController.class);

    @Autowired
    ResponseHandler requestHandler;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(method = RequestMethod.GET, value = "/get_directions")
    public ResponseEntity getSyncDirections() {
        try {
            String hostName = "https://maps.googleapis.com/maps/api/directions/json";
            String query = "?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
            ResponseEntity response = requestHandler.getSyncDirections(hostName, query, restTemplate);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            LOG.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_async_directions")
    public ResponseEntity getAsyncDirections(
            @RequestParam(value = "wait", defaultValue = "false") boolean waitForAsyncResult) {
        try {
            String hostName = "https://maps.googleapis.com/maps/api/directions/json";
            String query = "?origin=32732+bel+aire+ct+union+city&destination=fremont+bart+station&key=AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";

            Future<ResponseEntity> response = requestHandler.getAsyncDirections(hostName, query, restTemplate);
            return new ResponseEntity<>(response.get().getBody(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
