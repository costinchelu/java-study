package demo;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Behaviour parametrization */
public class BasicPredicate {

    private static final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

    public static void main(String[] args) {
        // print even:
        numbers.stream().filter(x -> x % 2 == 0).forEach(System.out::println);
        // print odd:
        numbers.stream().filter(x -> x % 2 != 0).forEach(System.out::println);

        // the two operations are pretty similar...
        Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        Predicate<Integer> oddPredicate = x -> x % 2 != 0;

        numbers.stream().filter(evenPredicate).forEach(System.out::println);
        numbers.stream().filter(oddPredicate).forEach(System.out::println);

        // to actually pass the logic of the method as an argument:
        filterAndPrint(numbers, x -> x % 2 == 0);

        // similarly, we can create methods that can receive a Function<T, R> as a parameter
        List<Integer> mappedList1 = mapAndReturnList(numbers, x -> x * 2);
        List<Integer> mappedList2 = mapAndReturnList(numbers, x -> x + x);

        Predicate<Integer> oddPredicate2 = evenPredicate.negate();
        Predicate<Integer> anyway = evenPredicate.or(oddPredicate);
		
		
		Predicate<String> notEmptyPredicate = s -> !s.isEmpty();

        System.out.println(notEmptyPredicate.test("foo"));
        System.out.println(notEmptyPredicate.negate().test("foo"));
		
		Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers.stream().filter(predicate).forEach(System.out::println);
    }

    private static List<Integer> mapAndReturnList(List<Integer> list, Function<Integer, Integer> mappingFunction) {
        return list.stream().map(mappingFunction).collect(Collectors.toList());
    }
}