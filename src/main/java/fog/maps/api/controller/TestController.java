package fog.maps.api.controller;

import fog.maps.api.model.TestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by nakulsharma on 15-11-2016.
 * Controller to test proper functioning of RestFul web service in different ways
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/test_with_pojo")
    public ResponseEntity testMethodWithPojo() {
        try {
            TestModel test = new TestModel();
            test.setId(1);
            test.setName("Team - Crazy 4");
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test_string")
    public ResponseEntity testMethodWithoutPojo() {
        try {
            String str = "Hello, Crazy 4";
            return new ResponseEntity<>(str, HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test_params")
    public ResponseEntity testMethodRequestParameters(
            @RequestParam(value = "user_id", required = true) String userId) {
        try {
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test_request_body")
    public ResponseEntity testMethodRequestBody(
            @RequestBody String userId) {
        try {
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.error("IllegalStateException: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}