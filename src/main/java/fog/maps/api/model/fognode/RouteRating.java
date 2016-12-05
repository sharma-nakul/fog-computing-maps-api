package fog.maps.api.model.fognode;

import fog.maps.api.model.direction.DirectionRoute;

/**
 * Created by nakulsharma on 05-12-2016.
 * Model of route rating POJO
 */
public class RouteRating {
    private Integer rating;
    private DirectionRoute route;

    public RouteRating(Integer rating, DirectionRoute route) {
        this.rating = rating;
        this.route = route;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public DirectionRoute getRoute() {
        return route;
    }

    public void setRoute(DirectionRoute route) {
        this.route = route;
    }
}
