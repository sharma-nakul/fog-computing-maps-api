package fog.maps.api.model.direction;

/**
 * Created by nakulsharma on 29-11-2016.
 * Holds values of query parameters to fetch the directions
 */
public class QueryParameter {

    private String origin;
    private String destination;
    private TravelMode travelMode;
    private String apiKey;
    private boolean alternatives;

    public QueryParameter(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
        //todo: Use property feature to get api key
        this.apiKey="AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
    }

    public QueryParameter(String origin, String destination, TravelMode travelMode) {
        this.origin = origin;
        this.destination = destination;
        this.travelMode = travelMode;
        this.apiKey="AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY";
    }

    public QueryParameter() {
    }

    public String getOrigin() {

        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isAlternatives() {
        return alternatives;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public TravelMode getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public String toString() {
        return "";
    }
}
