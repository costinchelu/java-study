package atomic;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMaps {

    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));

        String value = map.putIfAbsent("c3", "p1");
        System.out.println(value);    // p0

        map.replaceAll((key, val) -> "r2".equals(key) ? "d3" : val);
        System.out.println(map.get("r2"));    // d3

        map.compute("foo", (key, val2) -> val2 + val2);
        System.out.println(map.get("foo"));   // barbar

        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));   // boo was foo

        map.forEach((key, val3) ->
                System.out.printf("key: %s; value: %s; thread: %s\n", key, val3, Thread.currentThread().getName()));
    }
}
