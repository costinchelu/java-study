package demo;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream specializations
 */
public class IntermediateOpReduceRanged {

    public static void main(String[] args) {

        // Stream of produces objects(Integer)
        System.out.println(
                Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15).count());

        System.out.println(
                Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15).reduce(0, Integer::sum));

        // int array will produce a stream of int (not Integer)
        int[] intArr = {12, 9, 13, 4, 6, 2, 4, 12, 15};
        Arrays.stream(intArr)
                .average()
                .ifPresent(System.out::println);

        // IntStream produces a stream of (primitive) int
        System.out.println(
                IntStream.range(1, 10).sum());

        // rangeClosed will include also 10
        System.out.println(
                IntStream.rangeClosed(1, 10).sum());

        // we can iterate so that the stream series is of a custom type
        System.out.println(
                IntStream.iterate(1, elem -> elem * 2)
                .limit(10)
                .peek(System.out::println)
                .sum());

        // to collect IntStream to a list we need to box the integers
        System.out.println(
                IntStream.iterate(2, e -> e * 2)
                        .limit(10)
                        .boxed()
                        .collect(Collectors.toList()));

        // generate 10 random integers within the range 0 - 500
        List<Integer> randomInts = Stream.generate(Math::random)
                .mapToInt(n -> (int) (n * 1000))
                .filter(n -> n <= 500)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(randomInts);

        // Factorial operations gives very big numbers.
        // To use very big numbers we can convert int to BigInteger
        BigInteger reduceOperation = LongStream.rangeClosed(1, 10)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
        System.out.println(reduceOperation);

        final String SENTENCE = " Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscura ché la dritta via era smarrita ";
        Stream<Character> sentenceStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("Found " + countWords(sentenceStream) + " words");
    }

    private static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.counter();
    }
}


record WordCounter(int counter, boolean lastSpace) {

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }
}