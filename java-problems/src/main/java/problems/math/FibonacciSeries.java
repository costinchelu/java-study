package problems.math;

import java.util.stream.Stream;

public class FibonacciSeries {

    public static void main(String[] args) {

        // first, generate an infinite stream of int[] starting from 0, 1 (initial state),
        // so that the next array will have the second member and the sum of members of the previous array
        // we should limit the result (infinite stream)
        // to print the series, we actually need to print just the first member of each array
        Stream.iterate(
                new int[] { 0, 1 },
                t -> new int[] { t[1], t[0] + t[1] })
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}
