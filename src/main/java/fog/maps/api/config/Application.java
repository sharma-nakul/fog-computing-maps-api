package fog.maps.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by nakulsharma on 15-11-2016.
 * Main class for running the project. Note: Spring api will run through Tomcat that can be configured under
 * Tools->Edit Configuration and then select Tomcat Server and provide exploded war of this project.
 */

@Configuration
@ComponentScan(basePackages = "fog.maps.api")
@EnableAutoConfiguration
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /*GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey("AIzaSyDngvpXqERGAGvOAbyani3tgOoirZgBxSY");
        DirectionsApi.getDirections(geoApiContext,"32732+bel+aire+ct+union+city","fremont+bart+station");
        */
    }
}