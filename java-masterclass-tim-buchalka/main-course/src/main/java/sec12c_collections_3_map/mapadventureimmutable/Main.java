package sec12c_collections_3_map.mapadventureimmutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> listOfExits = new HashMap<>();
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java", listOfExits));

        //every time we enter a new location instance we will put a new exits map where
        // we will supply the number of exits for that location
        // We can use listOfExits to recreate that exits map for every location because the location instances
        // will remain with the address of the previous iteration of HashMap listOfExits

        listOfExits = new HashMap<>();
        listOfExits.put("W", 2);
        listOfExits.put("E", 3);
        listOfExits.put("S", 4);
        listOfExits.put("N", 5);
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building", listOfExits));

        listOfExits = new HashMap<>();
        listOfExits.put("N", 5);
        locations.put(2, new Location(2, "You are at the top of a hill", listOfExits));

        listOfExits = new HashMap<>();
        listOfExits.put("W", 1);
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring", listOfExits));

        listOfExits = new HashMap<>();
        listOfExits.put("N", 1);
        listOfExits.put("W", 2);
        locations.put(4, new Location(4, "You are in a valley beside a stream", listOfExits));

        listOfExits = new HashMap<>();
        listOfExits.put("S", 1);
        listOfExits.put("W", 2);
        locations.put(5, new Location(5, "You are in the forest", listOfExits));

        //setting the vocabulary values for the user input that will be converted to actual directions
        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("EXIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0) {
                break;
            }
            Map<String, Integer> exits = locations.get(loc).getExists();
            System.out.print("Available exits are ");
            for(String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }

            System.out.println("\nWhere to go?");
            String direction = scanner.nextLine().toUpperCase();

           if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word : words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
           }

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);
            }
            else {
                System.out.println("You cannot go in that direction!");
            }
        }

    }
}
