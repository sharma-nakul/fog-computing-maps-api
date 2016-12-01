package fog.maps.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nakulsharma on 15-11-2016.
 * This is a home controller class. Ideally, this class should not have endpoint other than "/". you can however add
 * support for multiple content type but no extra api endpoint.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    // Google API Key -> AIzaSyCUuMGzXs2TBy1pSOo7W2LbObmKRrVcLQo
}
