package sec14h_niotext;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


// Text NIO

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();


    public static void main(String[] args) throws IOException {

        // classes in java.nio package usually works with path instances (and not with file instances)
        Path locPath = FileSystems.getDefault().getPath("locations_NIO.txt");
        Path dirPath = FileSystems.getDefault().getPath("directions_NIO.txt");

        try (BufferedWriter locFile = Files.newBufferedWriter(locPath);
             BufferedWriter dirFile = Files.newBufferedWriter(dirPath)) {

            for(Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for(String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        dirFile.write(location.getLocationID() + ","
                                + direction + "," + location.getExits().get(direction) + "\n");
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    static {

        Path locPath = FileSystems.getDefault().getPath("locations_NIO.txt");
        Path dirPath = FileSystems.getDefault().getPath("directions_NIO.txt");

        try(Scanner scanner = new Scanner(Files.newBufferedReader(locPath))) {
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                                            // the last line in the locations_NIO will be empty (we used "\n"), that's why we are using hasNextLine
                int locId = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String locDescription = scanner.nextLine();         // content of rest of the line (after ",")
                locations.put(locId, new Location(locId, locDescription, null));

                System.out.println("Imported location: " + locId + ": " + locDescription);
                                            // for debugging purpose
            }
        }
        catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        try(BufferedReader dirFile = Files.newBufferedReader(dirPath)) {
            String input;

            while((input = dirFile.readLine()) != null) {
                                            // if last line read is null, it means we are at the last line of the text file (empty line)
               String[] data = input.split(",");
                                            // we now have an array (3 elements) with locationID on position 0, then the direction and then the corresponding destination
                                            // (only one direction+destination per line)
                int locId = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                Location location = locations.get(locId);
                                            // actually used from the Map, to insert exits (one by one)
                location.addExit(direction, destination);
            }
        }
        catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
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
