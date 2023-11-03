package sec12c_collections_3_map.mapadventureimmutable;

import java.util.HashMap;
import java.util.Map;

/*
To protect Location class we've implemented some measures such as:

fields are private and the final in this case helps reading code (the value is not modifiable once generated)

we've not provided setters fo any field

in case of getExits we are not returning the actual exits objects but only a copy of them
*/

// IMMUTABLE CLASS

/*
Now to fully protect the class we've removed addExits method and moved creation of exits in the constructor
 */

public class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exists;

    public Location(int locationID, String description, Map<String , Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exists = new HashMap<>(exits);
        }
        else {
            this.exists = new HashMap<>();
        }

        this.exists.put("Q", 0);
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExists() {
        return new HashMap<>(exists);
    }

}
