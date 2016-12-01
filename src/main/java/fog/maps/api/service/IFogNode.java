package fog.maps.api.service;

import fog.maps.api.model.route.FogNodeResponse;

/**
 * Created by nakulsharma on 29-11-2016.
 * Interface that provides method(s) to connect with Fog Nodes (Intel Edison Board)
 */
public interface IFogNode {

    FogNodeResponse getPollutionLevelWithCoordinates(String fogNodeUri);
}
