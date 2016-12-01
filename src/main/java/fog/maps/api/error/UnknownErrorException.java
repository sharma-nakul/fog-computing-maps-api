package fog.maps.api.error;

/**
 * Created by nakulsharma on 29-11-2016.
 * Indicates that unknown error has occurred at server side.
 */
public class UnknownErrorException extends ApiExceptions {

    public UnknownErrorException(String message) {
        super(message);
    }
}
