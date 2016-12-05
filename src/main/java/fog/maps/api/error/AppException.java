package fog.maps.api.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nakulsharma on 05-12-2016.
 * Application level exceptions
 */
public class AppException extends Exception {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    public AppException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
