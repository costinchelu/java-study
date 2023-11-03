package sec14g_randomaccess;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();
    private static final int STARTING_ROOM = 64;
                                    // this is random, as a starting point

    public static void main(String[] args) throws IOException {

	    Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        //int loc = 64;   //randomly chosen
        // some modifications are needed because now we cannot refer directly to locations (because they are not all loaded in the memory anymore)
        Location currentLocation = locations.getLocation(STARTING_ROOM);
        while(true) {
            System.out.println(currentLocation.getDescription());

            if(currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));
                                                // getExits returns a Map where the direction string is the key. Using get method for the map will actually return an integer as the value for that string key(direction)
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
        locations.close();
                    // when exiting with Q (quit), file locationsRand.dat should be closed
    }
}
