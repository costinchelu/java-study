package howto.map_interface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapsDemo {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 1; i < 11; i++) {
            map.putIfAbsent(i, "val-" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (id, val) -> val + id);
        System.out.println(map.get(3));

        map.computeIfPresent(9, (id, val) -> null);
        System.out.println(map.containsKey(9));

        map.computeIfAbsent(23, id -> "val-" + id);
        System.out.println(map.containsKey(23) + " " + map.get(23));

        map.putIfAbsent(3, "bam");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(42, "NOT FOUND"));

        map.remove(3, "val3");
        System.out.println(map.get(3));

        map.remove(3, "val-33");
        System.out.println(map.get(3) + " now removed...");

        map.merge(9, "val-9", String::concat);
        System.out.println(map.get(9) + " (it was initially removed from the map)");

        map.merge(9, "concat", String::concat);
        System.out.println(map.get(9));

        iterateUsingEntrySet(map);
    }

    public static <K, V> void iterateUsingEntrySet(Map<K, V> map) {
        System.out.println();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(
                    entry.getKey() +
                            " : " +
                    entry.getValue());
        }
    }

    public static <K, V> void iterateUsingKeySetAndForeach(Map<K, V> map) {
        for (K key : map.keySet()) {
            System.out.println(
                    key +
                            " : " +
                    map.get(key));
        }
    }

    public static <K, V> void iterateValues(Map<K, V> map) {
        for (V value : map.values()) {
            System.out.println(value);
        }
    }

    public static <K, V> void iterateUsingIteratorAndEntry(Map<K, V> map) {
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator(); // we can use map.keySet().iterator() OR map.values().iterator()
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static <K, V> void iterateUsingLambdaEntrySet(Map<K, V> map) {
        map.forEach((k, v) -> System.out.println((k + " : " + v)));
    }

    public <K, V> void iterateByKeysUsingLambdaKeySet(Map<K, V> map) {
        map.keySet().forEach(k -> System.out.println((k + " : " + map.get(k))));
    }

    public <K, V> void iterateValuesUsingLambdaValues(Map<K, V> map) {
        map.values().forEach(v -> System.out.println(("value: " + v)));
    }

    public <K, V> void iterateUsingStreamAPI(Map<K, V> map) {
        map.entrySet().stream()
                // ... some other Stream processings
                .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
    }

}
