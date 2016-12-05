package fog.maps.api.error;

/**
 * Created by nakulsharma on 05-12-2016.
 * Represents exception when requested resource cannot be geocoded or invalid.
 */
public class ResourceNotFoundException extends ApiExceptions{

    protected ResourceNotFoundException(String message) {
        super(message);
    }
}
