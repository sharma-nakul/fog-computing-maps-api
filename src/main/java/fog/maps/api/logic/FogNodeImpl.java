package fog.maps.api.logic;

import fog.maps.api.service.IFogNode;
import fog.maps.api.model.route.FogNodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nakulsharma on 29-11-2016.
 * Implementation of IFogNode interface to connect with IoT device and get data
 */
@Service
public class FogNodeImpl implements IFogNode {

    private static final Logger logger = LoggerFactory.getLogger(FogNodeImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Override
    public FogNodeResponse getPollutionLevelWithCoordinates(String fogNodeUri) {
        try {
            //Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            FogNodeResponse pollutionLevelWithCoordinates = restTemplate.getForObject(fogNodeUri, FogNodeResponse.class);
            logger.info("Pollution level fetched successfully from Fog Node");
            return pollutionLevelWithCoordinates;
        } catch (Exception e) {
            logger.error("Error in fetching pollution level from Fog Node", e.getMessage());
            return null;
        }
    }
}
