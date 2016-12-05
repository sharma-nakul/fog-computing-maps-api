package fog.maps.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by nakulsharma on 04-12-2016.
 * Model class for error
 */

public class Error implements Serializable{

    @JsonProperty("error_message")
    private String errorMessage;
    private String Status;

    public Error() {
    }

    public Error(String errorMessage, String status) {
        this.errorMessage = errorMessage;
        Status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
