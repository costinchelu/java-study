package sec12c_collections_3_map.mapadventure;

import java.util.HashMap;
import java.util.Map;

/*
To protect Location class we've implemented some measures such as:

fields are private and the final in this case helps reading code (the value is not modifiable once generated)

we've not provided setters fo any field

in case of getExits we are not returning the actual exits objects but only a copy of them
*/

public class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exists;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;

        this.exists = new HashMap<>();
        this.exists.put("Q", 0);    //coded in constructor because we can quit from every location
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExists() {
        return new HashMap<>(exists);
        //creates a new HashMap (instead of directly returning exists
        //has "exists" in the constructor
        //so a new map is created that has all the data of the "exists" Map
        //getter returns a copy of the actual exists and we can work with that copy instead of
        //risking failure of the original "exists" Map
    }

    public void addExit(String direction, int location) {
        exists.put(direction, location);
    }
}
