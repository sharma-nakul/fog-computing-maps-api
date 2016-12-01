package fog.maps.api.service;

import fog.maps.api.error.ApiExceptions;

/**
 * Created by nakulsharma on 29-11-2016.
 * All the api responses should implement this interface.
 * It ensures that api performing minimum level of checks using this interface.
 * T - Class that holds the response.
 */
public interface ApiResponse<T> {
    boolean success();
    T getResponse();
    ApiExceptions getError();
}
