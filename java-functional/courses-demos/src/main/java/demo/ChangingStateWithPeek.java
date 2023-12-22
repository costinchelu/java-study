package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *     Remember that {@code peek()} is intended to perform an operation without changing the result.
 * </p>
 * <p>
 *     This example is bad because {@code peek()} is modifying the data structure that is used in the
 * stream, which causes the result of the stream pipeline to be different than if the peek
 * wasn’t present.
 * </p>
 */
public class ChangingStateWithPeek {

    public static void main(String[] args) {

        var numbers = new ArrayList<>();
        var letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');
        Stream<List<?>> stream = Stream.of(numbers, letters);
        stream.map(List::size).forEach(System.out::print); // 11



        Stream<List<?>> bad = Stream.of(numbers, letters);
        bad.peek(x -> x.remove(0))
                .map(List::size)
                .forEach(System.out::print); // 00
    }
}
