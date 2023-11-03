package howto.map_interface;

import java.util.HashMap;
import java.util.Map;

public class MapsDemo {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 1; i < 11; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (id, val) -> val + id);
        System.out.println(map.get(3));

        map.computeIfPresent(9, (id, val) -> null);
        System.out.println(map.containsKey(9));

        map.computeIfAbsent(23, id -> "val" + id);
        System.out.println(map.containsKey(23) + " " + map.get(23));

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(42, "NOT FOUND"));

        map.remove(3, "val3");
        System.out.println(map.get(3));

        map.remove(3, "val33");
        System.out.println(map.get(3) + " now removed...");

        map.merge(9, "val9", String::concat);
        System.out.println(map.get(9) + " (it was initially removed from the map)");

        map.merge(9, "concat", String::concat);
        System.out.println(map.get(9));
    }
}
