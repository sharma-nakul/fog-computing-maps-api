package fog.maps.api.error;

/**
 * Created by nakulsharma on 29-11-2016.
 * Indicates that the api received an invalid or incorrect request from the user.
 */
public class InvalidRequestException extends ApiExceptions{

    public InvalidRequestException(String message) {
        super(message);
    }


}
