package fog.maps.api.internal;

import fog.maps.api.model.direction.Coordinate;
import fog.maps.api.model.direction.DirectionLeg;
import fog.maps.api.model.direction.DirectionRoute;
import fog.maps.api.model.direction.DirectionStep;
import fog.maps.api.model.fognode.NodeCoordinate;
import fog.maps.api.model.fognode.NodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by nakulsharma on 02-12-2016.
 * Represents methods/functionality of pollution data reading from Fog Node
 */

@Component
public class Pollution {

    private static Logger LOG = LoggerFactory.getLogger(Pollution.class);

    private Map<String, Coordinate> getUniqueCoordinates(DirectionRoute[] routes) {
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
        return waypoints;
    }


    private Map<String, Integer> getPollutionRating(NodeResult nodeResults) {
        ArrayList<NodeResult> fogResultList = new ArrayList<>(Arrays.asList(nodeResults));
        Map<String, Integer> pollutionRating = new HashMap<>();
        for (NodeResult result : fogResultList) {
            for (NodeCoordinate coordinate : result.getCoordinates()) {
                String key = Base64.getEncoder().encodeToString((coordinate.getLat() + "," + coordinate.getLng()).getBytes());
                int value;
                switch (coordinate.getPollutionLevel().name()) {
                    case "HIGH":
                        value = 10;
                        break;
                    case "MEDIUM_HIGH":
                        value = 8;
                        break;
                    case "MEDIUM":
                        value = 6;
                        break;
                    case "MEDIUM_LOW":
                        value = 4;
                        break;
                    case "LOW":
                        value = 2;
                        break;
                    default:
                        value = 0;
                        break;
                }
                pollutionRating.putIfAbsent(key, value);
            }
        }
        return pollutionRating;
    }


    public Map<DirectionRoute, Integer> getRouteRatings(DirectionRoute[] routes, NodeResult nodeResult) {
        Map<DirectionRoute, Integer> routeRating = new HashMap<>();
        Map<String, Integer> pollutionRating = getPollutionRating(nodeResult);
        for (DirectionRoute r : routes) {
            int rating = 0;
            for (DirectionLeg l : r.getLegs()) {
                for (DirectionStep step : l.getSteps()) {
                    String startCoordinates = Base64.getEncoder().encodeToString((step.getStartLocation().getCoordinates()).getBytes());
                    String endCoordinates = Base64.getEncoder().encodeToString((step.getEndLocation().getCoordinates()).getBytes());
                    if (pollutionRating.containsKey(startCoordinates))
                        rating += pollutionRating.get(startCoordinates);
                    else if (pollutionRating.containsKey(endCoordinates))
                        rating += pollutionRating.get(endCoordinates);
                    else
                        continue;
                }
            }
            routeRating.putIfAbsent(r, rating);
            LOG.info("Route rating is {}", rating);
        }
        return routeRating;
    }

    public DirectionRoute getBestRoute(DirectionRoute[] routes, NodeResult nodeResult) {
        Map<DirectionRoute, Integer> routeRating = getRouteRatings(routes, nodeResult);
        Map.Entry<DirectionRoute, Integer> maxRating = null;
        for (Map.Entry<DirectionRoute, Integer> entry : routeRating.entrySet()) {
            if (maxRating == null || entry.getValue().compareTo(maxRating.getValue()) > 0) {
                maxRating = entry;
            }
        }
        return maxRating.getKey();
    }
}
