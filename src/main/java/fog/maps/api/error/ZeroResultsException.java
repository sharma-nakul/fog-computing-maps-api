package fog.maps.api.error;

/**
 * Created by nakulsharma on 05-12-2016.
 * When no data found for a requested resources.
 */
public class ZeroResultsException extends ApiExceptions {

    protected ZeroResultsException(String message) {
        super(message);
    }
}
