package demo;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class BasicConsumer {

    public static void main(String[] args) {
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);

        c1.accept("Annie"); // Annie
        c2.accept("Annie"); // Annie

        var map = new HashMap<String, Integer>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        System.out.println(map); // {chicken=7, chick=1}
        // BiConsumer can use the same type for both the T and U generic parameters.
    }
}
