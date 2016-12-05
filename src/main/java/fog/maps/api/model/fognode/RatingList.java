package fog.maps.api.model.fognode;

import java.util.List;

/**
 * Created by nakulsharma on 05-12-2016.
 * List of ratings for all routes.
 */
public class RatingList {

    private List<RouteRating> routes;

    public RatingList(List<RouteRating> routes) {
        this.routes = routes;
    }

    public List<RouteRating> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteRating> routes) {
        this.routes = routes;
    }
}
