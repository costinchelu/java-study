package sec14e_bytestreams;

import java.io.*;
import java.util.*;

// Byte Streams when dealing with data

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {

        /*
        when using binary files we need to know the exact format of the data written and then read from the file
        For example in the next method we have (in the exact order):
            1. Integer (location ID)
            2. String ( = UTF. Location description)
            3. Integer (no. of exits - used to know how many to write/read)
            4. variable number of exits:
                    4a. String (a character for direction)
                    4b. Integer (the actual location where direction points to)
        */

        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {

            for(Location location : locations.values()) {
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                locFile.writeInt(location.getExits().size() - 1);

                System.out.println("Writing location " + location.getLocationID() + " :" + location.getDescription());
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");

                for(String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));

                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                    }
                }
            }
        }
    }

    static {

        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {

            boolean eof = false;    //break condition for the while loop. When we get an EOFException, will exit while

            while(!eof) {
                try {
                    Map<String, Integer> locExits = new LinkedHashMap<>();

                    int locID = locFile.readInt();
                    String locDescription = locFile.readUTF();
                    int numExits = locFile.readInt();

                    System.out.println("Read location " + locID + " : " + locDescription);
                    System.out.println("Found " + numExits + " exits");

                    for(int i = 0; i < numExits; i++) {
                        String direction = locFile.readUTF();
                        int destination = locFile.readInt();
                        locExits.put(direction, destination);

                        System.out.println("\t\t" + direction + "," + destination);
                    }
                    locations.put(locID, new Location(locID, locDescription, locExits));
                }
                catch (EOFException e) {
                    eof = true;
                }
            }
        }
        catch (IOException e) {
            System.out.println("IOException");
        }

    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {   }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
