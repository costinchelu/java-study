package demo;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("w", "o", "l", "f", "e", "n", "s", "t", "e", "i", "n", " ", "c", "a", "s", "t", "l", "e");
        // for parallel processing a combiner is recommended
        // make sure that the accumulator and combiner produce the same result regardless of the order they are called in
        String word = stream.parallel().reduce("", (str, chr) -> str + chr, (s1, s2) -> s1 + s2);
        System.out.println(word);



        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> map = ohMy.collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(map); // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap

    }
}
