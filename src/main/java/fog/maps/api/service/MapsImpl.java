package fog.maps.api.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fog.maps.api.model.direction.Coordinate;
import fog.maps.api.model.direction.DirectionLeg;
import fog.maps.api.model.direction.DirectionRoute;
import fog.maps.api.model.direction.DirectionStep;
import fog.maps.api.model.route.*;
import fog.maps.api.model.test.JsonRequestCoordinate;
import fog.maps.api.model.test.JsonRequestObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by nakulsharma on 22-11-2016.
 * Implements methods of IPollutionCheck interface
 */

@Service
public class MapsImpl implements IMaps {

    private static final Logger logger = LoggerFactory.getLogger(MapsImpl.class);

    @Override
    public RouteCoordinates getUniqueCoordinatesFromAllDirections(DirectionRoute[] routes) {
        Map<String, Coordinate> waypoints = new HashMap<>();
        for (DirectionRoute route : routes) {
            for (DirectionLeg leg : route.getLegs()) {
                for (DirectionStep step : leg.getSteps()) {
                    Coordinate tempStartWaypoint = new Coordinate(step.getStartLocation().getLat(), step.getStartLocation().getLng());
                    Coordinate tempEndWaypoint = new Coordinate(step.getEndLocation().getLat(), step.getEndLocation().getLng());
                    String startCoordinates = Base64.getEncoder().encodeToString((step.getStartLocation().getCoordinates()).getBytes());
                    String endCoordinates = Base64.getEncoder().encodeToString((step.getEndLocation().getCoordinates()).getBytes());
                    /*if (waypoints.isEmpty()) {
                        waypoints.put(startCoordinates,tempStartWaypoint);
                        waypoints.put(endCoordinates,tempEndWaypoint);
                    } else {*/
                    waypoints.putIfAbsent(startCoordinates, tempStartWaypoint);
                    waypoints.putIfAbsent(endCoordinates, tempEndWaypoint);
                    //}
                }
            }
        }
        if (waypoints.isEmpty()) {
            logger.info("Zero waypoints found for this direction");
            return null;
        }
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate c : waypoints.values())
            coordinates.add(c);
        RouteCoordinates points = new RouteCoordinates(coordinates);
        return points;
    }

    @Override
    public FogNodeResponse getResponseFromFogNode(){
       try {
           Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
           logger.info("Pollution level received from Fog Nodes");
           String filePath="C:\\Users\\nakulsharma\\Documents\\295B - Project\\fog.maps.api\\src\\main\\resources\\iot.json";
           BufferedReader br = new BufferedReader(new FileReader(filePath));
           return gson.fromJson(br,FogNodeResponse.class);
       }catch(Exception e){
           logger.error(e.getMessage());
           e.printStackTrace();
           return null;
       }
    }

    //Temporary helper method to create json
    //todo: delete helper method when original data is available from fog node
    private String writeJsonFile(){
        JsonRequestObject jsonObject=new JsonRequestObject();
        jsonObject.setStatus(FogNodeStatusCode.OK);
        List<JsonRequestCoordinate> coordinates = new ArrayList<>();

        //Adding coordinates
        JsonRequestCoordinate c1=new JsonRequestCoordinate();
        c1.setLat(37.5780743);
        c1.setLng(-122.0472379);
        c1.setPollutionLevel(PollutionLevel.HIGH);
        coordinates.add(c1);
        JsonRequestCoordinate c2=new JsonRequestCoordinate();
        c2.setLat(37.5642754);
        c2.setLng(-121.9795471);
        c2.setPollutionLevel(PollutionLevel.MEDIUM_HIGH);
        coordinates.add(c2);
          jsonObject.setCoordinates(coordinates);
        Gson gson=new Gson();
        String json= gson.toJson(jsonObject);
        logger.info(json);
        try{
            FileWriter writer=new FileWriter("C:\\Users\\nakulsharma\\Desktop\\pollution.json");
            writer.write(json);
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
