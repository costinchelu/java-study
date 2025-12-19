package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class HigherOrderExample {

    private static final List<Integer> numbers = List.of(1, 2, 3);
    private static final List<String> strings = List.of("a", "b", "c");

    private static Integer squared(Integer input) {
        return input * input;
    }

    private static String joined(String input) {
        return input + input;
    }

    // same thing Function<T, R>, but because type T is the same as type R, we can use an UnaryOperator
    private static <T, R> List<R> applyFunctionToList(List<T> inputList, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : inputList) {
            result.add(function.apply(item));
        }
        return result;
    }

    private static <T> List<T> applyFunctionToList(List<T> inputList, UnaryOperator<T> op) {
        List<T> result = new ArrayList<>();
        for (T item : inputList) {
            result.add(op.apply(item));
        }
        return result;
    }

    public static void main(String[] args) {
//        List<Integer> squares = applyFunctionToList(numbers, it -> HigherOrderExample.squared(it));
        List<Integer> squares = applyFunctionToList(numbers, HigherOrderExample ::squared);
        List<String> joined = applyFunctionToList(strings, HigherOrderExample::joined);
    }
}
