package fog.maps.api.service;

import fog.maps.api.logic.HttpRequestHandler;
import fog.maps.api.model.direction.DirectionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by nakulsharma on 30-11-2016.
 * Implementation class for MapsRequestHandler interface
 */

@Service
public class ResponseHandlerBean implements ResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseHandlerBean.class);


    @Override
    @Async
    public Future<ResponseEntity> getAsyncDirections(String hostName, String query, RestTemplate restTemplate) {
        LOG.info("Direction request url: " + hostName + query);
        LOG.info("> Fetching Directions");
        String uri = hostName + query;
        ResponseEntity<DirectionResult> responseEntity;
        CompletableFuture<ResponseEntity> response = new CompletableFuture<>();
        try {
            ParameterizedTypeReference typeReference=new ParameterizedTypeReference<DirectionResult>(){};
            HttpRequestHandler responseHandler=new HttpRequestHandler(restTemplate);
            responseEntity = responseHandler.makeGetRequest(uri, DirectionResult.class,typeReference);
            response.complete(responseEntity);
        } catch (Exception e) {
            LOG.warn("Exception caught while fetching directions asynchronously.", e);
            response.completeExceptionally(e);
        }
        LOG.info("< Fetching Directions");
        return response;
    }

    @Override
    public ResponseEntity getSyncDirections(String hostName, String query, RestTemplate restTemplate) {
        String uri = hostName + query;
        ResponseEntity<DirectionResult> responseEntity;
        try {
            ParameterizedTypeReference typeReference=new ParameterizedTypeReference<DirectionResult>(){};
            HttpRequestHandler responseHandler=new HttpRequestHandler(restTemplate);
            responseEntity = responseHandler.makeGetRequest(uri, DirectionResult.class,typeReference);
        } catch (Exception e) {
            LOG.warn("Exception caught while fetching directions asynchronously.", e);
            return null;
        }
        return responseEntity;
    }
}
