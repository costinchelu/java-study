package demo;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Basics {

    private static final List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

    public static void main(String[] args) {

        numbers.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .forEach(System.out::println);

        // can be written like :

        numbers.stream()
                .filter(Basics::isEven)
                .map(Basics::squared)
                .forEach(Basics::printX);

        // so each type of operation can be written like this also:
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer = System.out::println;

        numbers.stream()
                .filter(predicate)
                .map(function)
                .forEach(consumer);

        // actually to each operation we are passing the implementation of the abstract method of each of the functional interfaces:

        Predicate<Integer> predicateImpl = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return isEven(integer);
            }
        };

        Function<Integer, Integer> functionImpl = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return squared(integer);
            }
        };

        Consumer<Integer> consumerImpl = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                printX(integer);
            }
        };

        Supplier<Double> supplyDouble = new Supplier<Double>() {
            @Override
            public Double get() {
                return getRandomDouble();
            }
        };

        // So the compiler is actually taking the lambda expression and creates the implementation of that particular functional interface
        // the lambda expression would be passed like an object.

    }
    // FUNCTIONAL INTERFACES = interface with only one abstract method (function descriptor)


    // function.Predicate = Represents a predicate (boolean-valued function) of one argument.
    private static boolean isEven(int x) {
        return x % 2 == 0;
    }
    // public abstract Stream<T> filter(java.util.function.Predicate<? super T> predicate)


    // function.Function = Represents a function that accepts one argument and produces a result.
    private static int squared(int x) {
        return x * x;
    }
    // public abstract <R> Stream<R> map(java.util.function.Function<? super T, ? extends R> mapper)


    // function.Consumer = Represents an operation that accepts a single input argument and returns no result.
    private static <N extends Number> void printX(N x) {
        System.out.println(x);
    }
    // public abstract void forEach(java.util.function.Consumer<? super T> action)

    // function.Supplier = Represents an operation that accepts no input argument and returns a result
    private static Double getRandomDouble() {
        return new Random().nextDouble();
    }
}
