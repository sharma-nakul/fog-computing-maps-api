package fog.maps.api.internal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fog.maps.api.model.fognode.FogResponse;
import fog.maps.api.model.fognode.FogRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by nakulsharma on 01-12-2016.
 * Represents the complete response of Fog Node Api
 */
@Component
public class FogNodeApi {
    private static Logger LOG = LoggerFactory.getLogger(FogNodeApi.class);

    final ResponseHandler responseHandler;

    @Autowired
    public FogNodeApi(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public FogNodeApi() {
        responseHandler = null;
    }

    public FogResponse getFogNodeResponseFromFile(String fileName) {
        try {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            LOG.info("Pollution level received from Fog Nodes");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            return gson.fromJson(br, FogResponse.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public FogResponse getResponseFromFogNode(FogConfig config, String requestQuery, RestTemplate restTemplate) {
        try {
            String query = config.hostName + requestQuery;
            FogResponse pollutionLevelWithCoordinates = restTemplate.getForObject(query, FogResponse.class);
            LOG.info("Pollution level fetched successfully from Fog Node");
            return pollutionLevelWithCoordinates;
        } catch (Exception e) {
            LOG.error("Error in fetching pollution level from Fog Node", e.getMessage());
            return null;
        }
    }

    public FogResponse getFogNodeRouteRatingResponse(String targetUrl, FogRequest coordinates, RestTemplate template) {
        return template.postForObject(targetUrl, coordinates, FogResponse.class);
    }
}
