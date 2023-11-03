package demo;

import java.util.List;
import java.util.function.BinaryOperator;

public class BasicBinaryOperator {

    private static final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

    public static void main(String[] args) {

        int sumOfNumbers = numbers.stream().reduce(0, Integer::sum);

        //  extracting the method reference:
        BinaryOperator<Integer> sumOperator = Integer::sum;

        sumOfNumbers = numbers.stream().reduce(0, sumOperator);

        // public abstract T reduce(T identity, java.util.function.BinaryOperator<T> accumulator)
        // binary operator is another functional interface which extends BiFunction<T, U, R> interface
        // BiFunction<T, U, R> accepts two arguments and produces a result. This is the two-arity specialization of Function.

        // the abstract method of the functional interface:
        BinaryOperator<Integer> binaryOperatorImpl = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

    }
}
