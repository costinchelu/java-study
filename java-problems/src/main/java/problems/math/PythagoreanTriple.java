package problems.math;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Pythagorean Triples
 Triples of numbers (a, b, c) that satisfy the formula:
 a * a + b * b = c * c
 where a, b, and c are integers.
 For example, (3, 4, 5) is a valid Pythagorean triple because 3 * 3 + 4 * 4 = 5 * 5 or 9 + 16 = 25.
 There are an infinite number of such triples.
 Such triples are useful because they describe the three side lengths of a right-angled triangle.
 */
public class PythagoreanTriple {

    public static void main(String[] args) {
        List<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        // whether the square root of a * a + b * b is an integer number;
                        // that is, it has no fractional part,
                        // which in Java can be expressed using expr % 1.0.
                        // If it's not an integer, that means c is not an integer.
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }))
                        // filter only the triples that have values less or eq to 100
                        .filter(o -> Arrays.stream(o).noneMatch(n -> n > 100))
                .collect(Collectors.toList());

        pythagoreanTriples.stream().map(Arrays::toString).forEach(System.out::println);
    }
}
