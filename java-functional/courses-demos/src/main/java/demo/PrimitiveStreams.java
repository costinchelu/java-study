package demo;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreams {

    public static void main(String[] args) {
        var random = DoubleStream.generate(Math::random);
        var fractions = DoubleStream.iterate(.5, d -> d / 2);
        random.limit(10).forEach(System.out::println);
        fractions.limit(10).forEach(System.out::println);


        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::print); // 12345


        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(System.out::print); // 12345


        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream = objStream.mapToInt(String::length);

        var stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average();
        optional.ifPresent(System.out::println); // 5.5
        System.out.println(optional.getAsDouble()); // 5.5
        System.out.println(optional.orElseGet(() -> Double.NaN)); // 5.5
    }

    private static Stream<Integer> mapping(IntStream stream) {
        return stream.mapToObj(x -> x);
    }
    private static Stream<Integer> boxing(IntStream stream) {
        return stream.boxed();
    }
}
