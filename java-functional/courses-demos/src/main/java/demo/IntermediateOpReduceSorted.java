package demo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class IntermediateOpReduceSorted {

    private static final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
    private static final List<String> courses = List.of("Java SE", "Spring Boot", "SQL", "NoSQL", "Docker", "Linux", "Javascript", "HTML", "CSS");

    public static void main(String[] args) {

        int sum = numbers.stream().reduce(0, Integer::sum);
        int sum2 = numbers.stream().mapToInt(n -> n).sum();

        int max = numbers.stream().reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);

        int min = numbers.stream().reduce(Integer.MAX_VALUE, (x, y) -> x < y ? x : y);
        Optional<Integer> min2 = numbers.stream().min(Comparator.comparingInt(a -> a));
        Optional<Integer> min3 = numbers.stream().min((a, b) -> a - b);

        int evenNoSumOfSquares = numbers.stream().filter(x -> x % 2 == 0).map(x -> x * x).reduce(0, Integer::sum);

        numbers.stream().distinct().sorted().forEach(System.out::println);
        courses.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);

        Set<Integer> distinctSquaredNumbers = numbers.stream().map(x -> x * x).collect(Collectors.toSet());
        List<Integer> lengthsOfStrings = courses.stream().map(String::length).collect(Collectors.toList());

        System.out.println(sum);
        System.out.println(max);
        System.out.println(min);
        System.out.println(evenNoSumOfSquares);
        System.out.println(distinctSquaredNumbers);
        System.out.println(lengthsOfStrings);
    }
}
