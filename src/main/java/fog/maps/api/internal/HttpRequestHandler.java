package fog.maps.api.internal;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nakulsharma on 30-11-2016.
 * Defines method(s) to handle HTTP response
 */
public class HttpRequestHandler {

    private RestTemplate restTemplate;

    public HttpRequestHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> makeGetRequest(String uri, ParameterizedTypeReference typeRef) {

        ResponseEntity<T> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                typeRef);
        return response;
    }
}
