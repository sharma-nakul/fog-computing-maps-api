package fog.maps.api.internal;

import fog.maps.api.model.direction.DirectionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by nakulsharma on 30-11-2016.
 * Implementation class for MapsRequestHandler interface
 */

@Service
public class ResponseHandlerBean implements ResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseHandlerBean.class);

    @Autowired
    RestTemplate restTemplate;

    @Override
    @Async
    public <T> Future<ResponseEntity<T>> asyncGet(MapsConfig config, String query, Class clazz, Map<Class<?>, ParameterizedTypeReference> typeReferences) {
        String requestUri = config.hostName + config.path + query;
        LOG.info("Asynchronous request url: " + requestUri);
        LOG.info("> Asynchronous GET");

        ResponseEntity<T> responseEntity;
        CompletableFuture<ResponseEntity<T>> response = new CompletableFuture<>();
        try {
            ParameterizedTypeReference typeReference = new ParameterizedTypeReference<DirectionResult>() {
            };
            HttpRequestHandler responseHandler = new HttpRequestHandler(restTemplate);
            responseEntity = responseHandler.makeGetRequest(requestUri, typeReference);
            response.complete(responseEntity);
        } catch (Exception e) {
            LOG.error("Exception caught while performing asynchronous GET. {}", e.getMessage());
            response.completeExceptionally(e);
        }
        LOG.info("< Asynchronous GET");
        return response;
    }

    @Override
    public <T> ResponseEntity<T> synchronousGet(MapsConfig config, String query, Class clazz, Map<Class<?>, ParameterizedTypeReference> typeReferences) {
        String uri = config.hostName + config.path + query;
        LOG.info("Synchronous request url: "+uri);
        LOG.info("> Synchronous GET");
        ResponseEntity<T> responseEntity;
        try {
            ParameterizedTypeReference typeRef = typeReferences.get(clazz);
            HttpRequestHandler responseHandler = new HttpRequestHandler(restTemplate);
            responseEntity = responseHandler.makeGetRequest(uri, typeRef);
        } catch (Exception e) {
            LOG.error("Exception caught while performing synchronous GET. {}", e.getMessage());
            return null;
        }
        LOG.info("< Synchronous GET");
        return responseEntity;
    }
}
