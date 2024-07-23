package howto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class UsingTreeMaps<K, V> {
    
    private void createTreeMap() {
        // Default constructor
        Map<K, V> treeMap1 = new TreeMap<>();

        // Constructor with a custom Comparator
//        TreeMap<K, V> treeMap2 = new TreeMap<>(Comparator<? super K> comparator);

        // Constructor with an existing SortedMap
//        TreeMap<K, V> treeMap3 = new TreeMap<>(SortedMap<K, ? extends V> m);

        // Constructor with an existing Map
//        TreeMap<K, V> treeMap4 = new TreeMap<>(Map<? extends K, ? extends V> m);

        // Custom Comparator for sorting strings by length
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        TreeMap<String, Integer> treeMap5 = new TreeMap<>(lengthComparator);
        TreeMap<String, Integer> treeMap6 = new TreeMap<>(Map.of("A", 1, "B", 2, "C", 3));
    }

    private void treeMapMethods() {

        TreeMap<LocalDateTime, String> eventLog = new TreeMap<>();

        eventLog.put(LocalDateTime.of(2023, 5, 1, 10, 0), "System startup");
        eventLog.put(LocalDateTime.of(2023, 5, 1, 10, 15), "User login: Alice");
        eventLog.put(LocalDateTime.of(2023, 5, 1, 10, 30), "Database backup initiated");

        String event = eventLog.get(LocalDateTime.of(2023, 5, 1, 10, 15)); // Returns "User login: Alice"

        eventLog.remove(LocalDateTime.of(2023, 5, 1, 10, 30)); // Removes the database backup event

        boolean hasEvent = eventLog.containsKey(LocalDateTime.of(2023, 5, 1, 10, 0)); // Returns true

        boolean hasLoginEvent = eventLog.containsValue("User login: Alice"); // Returns true

        LocalDateTime earliestEvent = eventLog.firstKey(); // Returns 2023-05-01T10:00
        LocalDateTime latestEvent = eventLog.lastKey(); // Returns 2023-05-01T10:15

        // These methods return views of portions of the TreeMap.
        LocalDateTime cutoff = LocalDateTime.of(2023, 5, 1, 10, 10);
        SortedMap<LocalDateTime, String> earlierEvents = eventLog.headMap(cutoff);
        SortedMap<LocalDateTime, String> laterEvents = eventLog.tailMap(cutoff);
        SortedMap<LocalDateTime, String> eventsBetween = eventLog.subMap(
                LocalDateTime.of(2023, 5, 1, 10, 5),
                LocalDateTime.of(2023, 5, 1, 10, 20)
        );

        // These methods return the entry with the least key greater than or equal to the given key (ceiling) or the greatest key less than or equal to the given key (floor).
        LocalDateTime searchTime = LocalDateTime.of(2023, 5, 1, 10, 10);
        Map.Entry<LocalDateTime, String> nextEvent = eventLog.ceilingEntry(searchTime); // Returns entry for 10:15
        Map.Entry<LocalDateTime, String> previousEvent = eventLog.floorEntry(searchTime); // Returns entry for 10:00
        // These methods return the entry with the least key strictly greater than the given key (higher) or the greatest key strictly less than the given key (lower).
        LocalDateTime searchTime2 = LocalDateTime.of(2023, 5, 1, 10, 0);
        Map.Entry<LocalDateTime, String> nextEvent2 = eventLog.higherEntry(searchTime); // Returns entry for 10:15
        Map.Entry<LocalDateTime, String> previousEvent2 = eventLog.lowerEntry(searchTime); // Returns null (no earlier event)

        // These methods remove and return the first (earliest) or last (latest) entry in the TreeMap.
        Map.Entry<LocalDateTime, String> earliestEvent2 = eventLog.pollFirstEntry(); // Removes and returns entry for 10:00
        Map.Entry<LocalDateTime, String> latestEvent2 = eventLog.pollLastEntry(); // Removes and returns entry for 10:15

        // returns a reverse order view of the mappings in the TreeMap
        NavigableMap<LocalDateTime, String> reverseEventLog = eventLog.descendingMap();

        // The floorKey(K key) method returns the largest key in the TreeMap that is less than or equal to the given key. If there is no such key, it returns null.
        LocalDateTime searchTime3 = LocalDateTime.of(2023, 5, 1, 10, 20);
        LocalDateTime floorKey = eventLog.floorKey(searchTime); // Returns 2023-05-01T10:15
    }
}
