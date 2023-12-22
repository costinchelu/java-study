package demo;

import java.util.stream.Stream;

/**
 * <p>public T reduce(T identity, BinaryOperator<T> accumulator)</p>
 * <p>public Optional<T> reduce(BinaryOperator<T> accumulator)</p>
 * <p>public <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)</p>
 */
public class Reducing {

    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        String word = stream1.reduce("", (s, c) -> s + c);
//        String word = stream1.reduce("", String::concat);

        System.out.println(word); // wolf
    }
}
