package fog.maps.api.internal;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by nakulsharma on 30-11-2016.
 * Interface to handle google map request
 */
public interface ResponseHandler {

    <T> Future<ResponseEntity<T>> asyncGet(ApiConfig config, String query, Class clazz, Map<Class<?>, ParameterizedTypeReference> typeReferences);

    <T> ResponseEntity<T> synchronousGet(ApiConfig config, String query, Class clazz, Map<Class<?>, ParameterizedTypeReference> typeReferences);
}
