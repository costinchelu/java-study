package demo;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Stream;
import java.util.concurrent.*;
import java.util.Set;

/**
 * <p>public T reduce(T identity, BinaryOperator<T> accumulator)</p>
 * <p>public Optional<T> reduce(BinaryOperator<T> accumulator)</p>
 * <p>public <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)</p>
 */
public class Reducing {

    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        Stream<String> stream2 = Stream.of("w", "o", "l", "f", "e", "n", "s", "t", "e", "i", "n", " ", "c", "a", "s", "t", "l", "e");
        String word = stream1.reduce("", (str, chr) -> str + chr);
//        String word = stream1.reduce("", String::concat);

        // for parallel processing a combiner is recommended
        // make sure that the accumulator and combiner produce the same result regardless of the order they are called in
        String word2 = stream2.parallel().reduce("", (str, chr) -> str + chr, (s1, s2) -> s1 + s2);

        System.out.println(word); // wolf
        System.out.println(word2); // wolfenstein castle
    }
}
