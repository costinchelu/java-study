package demo;

import java.util.List;
import java.util.stream.Stream;

public class CreatingStreams {

    public static void main(String[] args) {

        // finite streams
        Stream<String> empty = Stream.empty();
        Stream<Integer> singleElement = Stream.of(1);
        Stream<Integer> fromArray = Stream.of(1, 2, 3);

        var stringList = List.of("A", "B", "C");
        Stream<String> fromList = stringList.stream();

        // infinite streams (use limit to turn it into a finite stream)
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        // there is an overloaded version of iterate that let us specify a predicate for the termination condition
        Stream<Integer> oddNumbersLessThan100 = Stream.iterate(1, n -> n < 100, n -> n + 2);
    }
}
