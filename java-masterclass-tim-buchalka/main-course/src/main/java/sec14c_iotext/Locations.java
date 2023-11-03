package sec14c_iotext;

import java.io.*;
import java.util.*;

//a class that behaves just like a Map but it can be customized from an external source
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    //this is the loner version:
    public static void longerMain(String[] args) {
        //Up until Java7, this was the method to write in a file:

        //Writing to txt:
        FileWriter locFile = null;

        //(Unhandled exception Java.io.IOException) Using a FileWriter object, implies a checked exception
        // (that means we have an obligation to construct a handler for that exception)
        try {
            locFile = new FileWriter("locations.txt");
            for(Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("In FileWriter catch block");
            e.printStackTrace();
        }
        finally {
            System.out.println("In FileWriter finally block");
            try {
                if(locFile != null) {
                    locFile.close();
                    System.out.println("FileWriter stream closed");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        // a try block must have a CATCH or a FINALLY block
        // code in finally will run regardless of what happened in the try block
        // in IO operations finally is usually present and usually because we want to be sure that the file stream
        // will be closed properly.
    }

    //this is the more affordable version:
    public static void shorterMain(String[] args) throws IOException {
        FileWriter locFile = null;
        try {
            locFile = new FileWriter("locations.txt");
            for(Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            }
            System.out.println("File written");
        }
        finally {
            if(locFile != null) {
                locFile.close();
            }
            System.out.println("File closed");
        }
    }

    //this is the try-with-resource introduced in java7
    //this "shortest method" will also deal with closing the stream (and will add the exits)
    public static void main(String[] args) throws IOException {
        try(FileWriter locFile = new FileWriter("locations.txt");
            FileWriter dirFile = new FileWriter("directions.txt")) {
            for(Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for(String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }
            }
        }
    }

    static {
//in a static block you cannot throw checked exceptions, so we need try-catch

        //reading locations the "long way" version
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new FileReader("locations_big.txt"));
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                int locID = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String locDescription = scanner.nextLine();
//                System.out.println("Imported location " + locID + ": " + locDescription);
//                Map<String, Integer> locExits = new HashMap<>();
//                locations.put(locID, new Location(locID, locDescription, locExits));
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if(scanner != null) {
//                scanner.close();
//            }
//        }

        //reading locations Java7 (try with resources)
        try(Scanner scanner = new Scanner(new FileReader("locations_big.txt"))) {
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int locID = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String locDescription = scanner.nextLine();
                System.out.println("Imported location " + locID + ": " + locDescription);
                Map<String, Integer> locExits = new HashMap<>();
                locations.put(locID, new Location(locID, locDescription, locExits));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //reading exits "the long way"
//        try {
//            scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {

                //reading and assigning values (ver 1)
//                int locID = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String locDirection = scanner.next();
//                scanner.skip(scanner.delimiter());
//                String tempDestination = scanner.nextLine();
//                int locDestination = Integer.parseInt(tempDestination);

                //reading and assigning values (ver 2)
//                String input = scanner.nextLine();
//                String[] data = input.split(",");
//                int locID = Integer.parseInt(data[0]);
//                String locDirection = data[1];
//                int locDestination = Integer.parseInt(data[2]);
//
//                System.out.println("Imported location " + locID + " exit: " + locDirection + ": " + locDestination);
//                Location location = locations.get(locID);
//                location.addExit(locDirection, locDestination);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            if(scanner != null) {
//                scanner.close();
//                //in this case, closing scanner will also close the FileReader stream because both
//                // scanner and FileReader implements Closeable interface
//                // notice that we need to use Scanner because BufferedReader throws unchecked exception
//            }
//        }

        //reading directions Java7 (try with resources)
        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            while((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int locID = Integer.parseInt(data[0]);
                String locDirection = data[1];
                int locDestination = Integer.parseInt(data[2]);

                System.out.println("Imported location " + locID + " exit: " + locDirection + ": " + locDestination);
                Location location = locations.get(locID);
                location.addExit(locDirection, locDestination);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        // No need for hardcoded Map because we can load that from the file(s)
//        Map<String, Integer> tempExit = new HashMap<String, Integer>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest",tempExit));
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
