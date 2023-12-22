package demo;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class SpliteratorExample {

    public static void main(String[] args) {
        var stream = List.of("bird-", "bunny-", "cat-", "dog-", "fish-", "lamb-", "mouse-");

        Spliterator<String> originalBagOfFood = stream.spliterator();

        Spliterator<String> emmasBag = originalBagOfFood.trySplit();
        emmasBag.forEachRemaining(System.out::print); // bird-bunny-cat-

        Spliterator<String> jillsBag = originalBagOfFood.trySplit();
        jillsBag.tryAdvance(System.out::print); // dog-
        jillsBag.forEachRemaining(System.out::print); // fish-

        originalBagOfFood.forEachRemaining(System.out::print); // lamb-mouse-


        var originalBag = Stream.iterate(1, n -> ++n).spliterator();
        Spliterator<Integer> newBag = originalBag.trySplit();
        newBag.tryAdvance(System.out::print); // 1
        newBag.tryAdvance(System.out::print); // 2
        newBag.tryAdvance(System.out::print); // 3
        /*
        Spliterator recognizes that the stream is infinite and doesn’t attempt to give you half. Instead, newBag
        contains a large number of elements. We get the first three since we call tryAdvance() three times.
        It would be a bad idea to call forEachRemaining() on an infinite stream!
         */
    }
}
