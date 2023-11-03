package venkats;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LightWeightStrategy {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // strategy all numbers
        System.out.println(totalValues(numbers, e -> true));
        // strategy even numbers only
        System.out.println(totalValues(numbers, LightWeightStrategy::isEven));
        // strategy odd numbers only
        System.out.println(totalValues(numbers, LightWeightStrategy::isOdd));
    }

    private static int totalValues(List<Integer> values, Predicate<Integer> selector) {
        int result = 0;
        return values.stream()
                .filter(selector)
                .reduce(result, Integer::sum);
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
