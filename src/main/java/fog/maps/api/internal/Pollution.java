package fog.maps.api.internal;

import fog.maps.api.model.direction.Coordinate;
import fog.maps.api.model.direction.DirectionLeg;
import fog.maps.api.model.direction.DirectionRoute;
import fog.maps.api.model.direction.DirectionStep;
import fog.maps.api.model.fognode.NodeCoordinate;
import fog.maps.api.model.fognode.FogResponse;
import fog.maps.api.model.fognode.FogRequest;
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

    public Map<DirectionRoute, Integer> getRouteRatings(DirectionRoute[] routes, FogResponse nodeResult) {
        Map<DirectionRoute, Integer> routeRating = new HashMap<>();
        Map<String, Integer> pollutionRating = getPollutionRating(nodeResult);
        int count=0;
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
                    else {
                        LOG.trace("Rating not found: {}",startCoordinates);
                        LOG.trace("Rating not found: {}",startCoordinates);
                        continue;
                    }
                }
            }
            routeRating.putIfAbsent(r, rating);
            LOG.info("Route {} rating = {}",++count, rating);
        }
        return routeRating;
    }

    public DirectionRoute getBestRoute(DirectionRoute[] routes, FogResponse nodeResult) {
        Map<DirectionRoute, Integer> routeRating = getRouteRatings(routes, nodeResult);
        Map.Entry<DirectionRoute, Integer> maxRating = null;
        for (Map.Entry<DirectionRoute, Integer> entry : routeRating.entrySet()) {
            if (maxRating == null || entry.getValue().compareTo(maxRating.getValue()) > 0) {
                maxRating = entry;
            }
        }
        return maxRating.getKey();
    }

    public FogRequest getUniqueCoordinatesFromAllDirections(DirectionRoute[] routes) {
        Map<String, Coordinate> waypoints = getUniqueCoordinates(routes);
        if (waypoints.isEmpty()) {
            LOG.info("Zero waypoints found for this direction");
            FogRequest routeCoordinates = new FogRequest();
        }
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate c : waypoints.values())
            coordinates.add(c);
        FogRequest points = new FogRequest(coordinates);
        return points;
    }

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

    private Map<String, Integer> getPollutionRating(FogResponse nodeResults) {
        ArrayList<FogResponse> fogResultList = new ArrayList<>(Arrays.asList(nodeResults));
        Map<String, Integer> pollutionRating = new HashMap<>();
        for (FogResponse result : fogResultList) {
            for (NodeCoordinate coordinate : result.getCoordinates()) {
                String key = Base64.getEncoder().encodeToString((coordinate.getLat() + "," + coordinate.getLng()).getBytes());
                pollutionRating.putIfAbsent(key, coordinate.getPollutionLevel());
                LOG.trace("Coordinate<{}> = PollutionLevel<{}>", key, coordinate.getPollutionLevel());
            }
        }
        return pollutionRating;
    }
}
