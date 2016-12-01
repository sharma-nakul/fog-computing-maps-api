package fog.maps.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by nakulsharma on 30-11-2016.
 * Interface to handle google map request
 */
public interface ResponseHandler {

    Future<ResponseEntity> getAsyncDirections(String hostName, String query, RestTemplate restTemplate);

    ResponseEntity getSyncDirections(String hostName, String query, RestTemplate restTemplate);
}
