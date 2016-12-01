package fog.maps.api.error;

/**
 * Created by nakulsharma on 29-11-2016.
 * Represent errors returned by Api as part of response
 *
 */
public class ApiExceptions extends Exception {

    protected ApiExceptions(String message){
        super(message); // pass this message to exception class
    }

    /**
     * Construct the exception based on status and/or error message returned from api. If the response is successful,
     * this method will return null.
     * @param status status field returned from API
     * @param errorMessage error message returned from API (This will be empty if status is OK).
     * @return Exception message based on status and error message
     */
    public static ApiExceptions get(String status, String errorMessage){
        if ("OK".equals(status)) {
            return null;
        }
        else if("INVALID_REQUEST".equals(status))
            return new InvalidRequestException(errorMessage);

        //If system reach here, something unexpected occurred which will be handled through this function.
        return new UnknownErrorException("An unexpected error occurred. Status: "+status+", Message: "+errorMessage);
    }
}

