package demo;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * The groupingBy() collector tells collect() that it should group all of the elements of
 * the stream into a Map. The function determines the keys in the Map. Each value in the Map is
 * a List of all entries that match that key.
 * <br>
 * Note that the function you call in groupingBy() cannot return null. It
 * does not allow null keys.
 */
public class GroupingStringExamples {

    public static void main(String[] args) {
        List<String> strings = List.of("a", "bb", "cc", "ddd");

        example1(strings);
        example2(strings);
        example3(strings);
        example4(strings);
        example5(strings);
        example6(strings);
        example7(strings);
        example8(strings);
        example9(strings);
        example10(strings);
        example11(strings);
        example12(strings);
        example13(strings);
    }

    private static void example1(List<String> strings) {
        Map<Integer, List<String>> e1 = strings.stream()
                .collect(groupingBy(String::length));

        System.out.println(e1.getClass() + " >> " + e1); // {1=[a], 2=[bb, cc], 3=[ddd]}
    }

    /*
    Suppose that we don’t want a List as the value in the map and prefer a Set instead.
    There’s another method signature that lets us pass a downstream collector.
    This is a second collector that does something special with the values.
     */
    private static void example2(List<String> strings) {
        Map<Integer, TreeSet<String>> e2 = strings.stream()
                .collect(groupingBy(String::length, toCollection(TreeSet::new)));

        System.out.println(e2.getClass() + " >> " + e2); // {1=[a], 2=[bb, cc], 3=[ddd]}
    }

    // We can even change the type of Map returned through yet another parameter.
    private static void example3(List<String> strings) {
        TreeMap<Integer, Set<String>> e3 = strings.stream()
                .collect(groupingBy(String::length, TreeMap::new, Collectors.toSet()));

        System.out.println(e3.getClass() + " >> " + e3); // {1=[a], 2=[bb, cc], 3=[ddd]}
    }

    private static void example4(List<String> strings) {
        Map<Integer, Long> e4 = strings.stream()
                .collect(groupingBy(String::length, counting()));

        System.out.println(e4.getClass() + " >> " + e4); // {1=1, 2=2, 3=1}
    }

    private static void example5(List<String> strings) {
        Map<Integer, String> result = strings.stream()
                .collect(groupingBy(String::length, joining(",", "[", "]")));

        System.out.println(result); // {1=[a], 2=[bb,cc], 3=[ddd]}
    }

    private static void example6(List<String> strings) {
        Map<Integer, List<String>> result = strings.stream()
                .collect(groupingBy(String::length, filtering(s -> !s.contains("c"), toList())));

        System.out.println(result); // {1=[a], 2=[bb], 3=[ddd]}
    }

    private static void example7(List<String> strings) {
        Map<Integer, Double> result = strings.stream()
                .collect(groupingBy(String::length, averagingInt(String::hashCode)));

        System.out.println(result); // {1=97.0, 2=3152.0, 3=99300.0}
    }

    private static void example8(List<String> strings) {
        Map<Integer, Integer> result = strings.stream()
                .collect(groupingBy(String::length, summingInt(String::hashCode)));

        System.out.println(result); // {1=97, 2=6304, 3=99300}
    }

    private static void example9(List<String> strings) {
        Map<Integer, IntSummaryStatistics> result = strings.stream()
                .collect(groupingBy(String::length, summarizingInt(String::hashCode)));

        System.out.println(result);
    }

    private static void example10(List<String> strings) {
        Map<Integer, Optional<String>> result = strings.stream()
                .collect(groupingBy(String::length, Collectors.maxBy(Comparator.comparing(String::toUpperCase))));

        System.out.println(result); // {1=Optional[a], 2=Optional[cc], 3=Optional[ddd]}
    }

    private static void example11(List<String> strings) {
        Map<Integer, TreeSet<String>> result = strings.stream()
                .collect(groupingBy(String::length,
                                mapping(String::toUpperCase,
                                        filtering(s -> s.length() > 1, toCollection(TreeSet::new)))));

        System.out.println(result); // {1=[], 2=[BB, CC], 3=[DDD]}
    }

    private static void example12(List<String> strings) {
        Map<Integer, String> result = strings.stream()
                .collect(groupingBy(String::length,
                                mapping(toStringList(),
                                        flatMapping(s -> s.stream().distinct(),
                                                filtering(s -> s.length() > 0,
                                                        mapping(String::toUpperCase,
                                                                reducing("", (s, s2) -> s + s2)))))));

        System.out.println(result); //result {1=A, 2=BC, 3=D}
    }

    private static Function<String, List<String>> toStringList() {
        return s -> s.chars()
                .mapToObj(String::valueOf)
                .map(Object::toString)
                .collect(toList());
    }



    private static void example13(List<String> strings) {
        String result = String.valueOf(strings.stream().collect(maxBy(Comparator.comparingInt(String::length))));
        System.out.println(result);
    }
}
