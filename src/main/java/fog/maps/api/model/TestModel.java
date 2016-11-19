package fog.maps.api.model;

import java.io.Serializable;

/**
 * Created by nakulsharma on 15-11-2016.
 * This is a model class to test the Spring MVC with model binding
 */

public class TestModel implements Serializable{

    private int id;
    private String name;


    public TestModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
