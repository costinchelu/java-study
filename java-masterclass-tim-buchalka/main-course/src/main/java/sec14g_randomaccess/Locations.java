package sec14g_randomaccess;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
* Random access files:
*
* 1.    bytes 0 - 3     Will contain the number of locations
* 2.    bytes 4 - 7     Will contain the start offset of the location section
* 3.    bytes 8 - 1699  Will contain the index (locationID, position (offset) and size of every object (Location))
*                       It will be no. of locations (141) * 3 * integer size(4 bytes)
* 4.    bytes > 1700    Will contain the location records (the data)
*
*       We will open the file in mode "rwd":
*
*                       Open for reading and writing, as with "rw", and also require
*                       that every update to the file's content be written synchronously
*                       to the underlying storage device.
*
*                       The "rwd" mode can be used to reduce the number of I/O operations performed.
*                       Using "rwd" only requires updates to the file's content to be written to storage;
*                       using "rws" requires updates to both the file's content and its metadata to be written,
*                       which generally requires at least one more low-level I/O operation.
*
* One drawback of random access files is that we can't write or read objects. So each piece of data must be written
* individually along with support data to position in the file.
* We also don't use BufferedStream because it wouldn't make sense without sequential read and write.
* Also there is no chaining with other methods of file IO
*/

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
                                                    // used to write the index of the random access file
    private static RandomAccessFile raf;
                                                    // used for the static read method


    public static void main(String[] args) throws IOException {

        try(RandomAccessFile locFile = new RandomAccessFile("locationsRand.dat", "rwd")) {
            locFile.writeInt(locations.size());
                                                                    // number of locations (bytes 0 - 3)
            int indexSize = locations.size() * 3 * Integer.BYTES;
                                                                    // calculating the index size (141*3*4=1692)
            int locationStart = (int) (Integer.BYTES + indexSize + locFile.getFilePointer());
                                                                    // calculating start offset of actual data will be (start of the data writing)
            locFile.writeInt(locationStart);
                                                                    // start offset(bytes 4 - 7)
            long indexStart = locFile.getFilePointer();
                                                                    // we use current position to memorize the start of index area (for later)
            int startPointer = locationStart;
                                                                    // this will be used to calculate the location's record length, after it was written to the file
            locFile.seek(startPointer);
                                                                    //position at the start of data area (calculated before). We only need to do this at the beginning, because after that we will write data sequentially
            for(Location location : locations.values()) {
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                StringBuilder builder = new StringBuilder();
                for(String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExits().get(direction));
                        builder.append(",");
                                                                    //  N,1,U,2,
                    }
                }
                locFile.writeUTF(builder.toString());
                                                                    //write the complete exits string (it will be something like:   direction, locationID, direction, locationID,)
                IndexRecord record = new IndexRecord(startPointer, (int) (locFile.getFilePointer() - startPointer));
                                                                    // create the index record: position of the data (startPointer) and length of data (current - initial position)
                index.put(location.getLocationID(), record);
                                                                    // in this case we will have locationID as first Integer.BYTE then offset and then size (resulted from calculation)
                startPointer = (int) locFile.getFilePointer();
                                                                    // update start pointer (offset) as current position (after the current location)
            }
            // after we've written all the locations, we will write the content of the index:
            locFile.seek(indexStart);
            for(Integer locationID : index.keySet()) {
                locFile.writeInt(locationID);
                locFile.writeInt(index.get(locationID).getStartByte());
                locFile.writeInt(index.get(locationID).getLength());
                // we will fetch the data from the index Map
            }

        }
    }

    static {
        // we won't be loading the locations into the memory anymore. We will load the locations on demand.

        try {
            raf = new RandomAccessFile("locationsRand.dat", "rwd");
            int numLocations = raf.readInt();
                                    // read the number of elements (for future reference)
            long locationStartPoint = raf.readInt();
                                    // read the offset of the location section
            while(raf.getFilePointer() < locationStartPoint) {
                                    // file pointer starts after locations offset (beginning byte 8, finishing at the end of index area of the file)
                int locationId = raf.readInt();
                int locationStart = raf.readInt();
                int locationLength = raf.readInt();
                                    //sequential reading of the 3 Integer bytes to build the record map (index)
                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationId, record);
                                    // this will build the IndexRecord  with all the locations offsets and lengths
            }
        }
        catch (IOException e) {
            System.out.println("IOException in static initializer: " + e.getMessage());
        }
    }

    // we need the getLocation method to extract (actually read) Location's data when needed
    // we will use this method every time the Player moves to a new location
    public Location getLocation(int locationId) throws IOException {
        IndexRecord record = index.get(locationId);
                                    // read location's offset and length from the index built in the static block
        raf.seek(record.getStartByte());
                                    // position pointer in the file at the start of the needed location
        int id = raf.readInt();     // it's the first integer in the index area. It could be used in case we want to compare with locationId provided
        String description = raf.readUTF();
                                    // readUTF method knows exactly how long the string is, because the writeUTF method will also put length of the string before the actual string
        String exits = raf.readUTF();
                                    // getting the actual data from the file
        String[] exitParts = exits.split(",");

        Location location = new Location(locationId, description, null);
                                    // initialize with null because this will construct a LinkedHashMap with position 0 already put (see Location constructor)
        // adding the exits:
        if(locationId != 0) {
            for(int i = 0; i < exitParts.length; i++) {
                // optional (displaying exits found)
                System.out.println("exitParts = " + exitParts[i]);
                System.out.println("++exitParts" + exitParts[i + 1]);

                String direction = exitParts[i];
                int destination = Integer.parseInt(exitParts[++i]);
                location.addExit(direction, destination);
            }
        }
        return location;
    }

    public void close() throws IOException {
        raf.close();
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
