package demo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>public <R> R collect(Supplier<R> supplier,
 * BiConsumer<R, ? super T> accumulator,
 * BiConsumer<R, R> combiner)</p>
 * <p>public <R,A> R collect(Collector<? super T, A,R/> collector)</p>
 */
public class TerminalOpCollecting {

    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        StringBuilder word = stream1.collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append);
        System.out.println(word); // wolf

        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set1 = stream2.collect(
                TreeSet::new,
                TreeSet::add,
                TreeSet::addAll);
        System.out.println(set1); // [f, l, o, w]

        Stream<String> stream3 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set2 = stream3.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set2); // [f, l, o, w]

        Stream<String> stream4 = Stream.of("w", "o", "l", "f");
        Set<String> set = stream4.collect(Collectors.toSet());
        System.out.println(set); // [f, w, l, o]

        var ohMy = List.of("lions", "tigers", "bears", "leopards");
        TreeSet<String> result = ohMy.stream()
                .filter(s -> s.startsWith("l"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result);         // [leopards, lions]

        // s -> s is actually Function.identity()
        Map<String, Integer> map = ohMy.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        String::length));
        System.out.println(map);            // {lions=5, bears=5, tigers=6}

        // add a constructor reference as a parameter = return a specific TreeMap
        TreeMap<Integer, String> map2 = ohMy.stream()
                .collect(Collectors.toMap(
                        String::length,
                        k -> k,
                        (s1, s2) -> s1 + "," + s2,
                        TreeMap::new));
        System.out.println(map2.getClass() + " >> " + map2);
        // class java.util.TreeMap >> {5=lions,bears, 6=tigers, 8=leopards}
    }
}
