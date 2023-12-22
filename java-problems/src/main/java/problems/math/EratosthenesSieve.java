package problems.math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 *     Sieve of Eratosthenes
 * </p>
 * <p>
 *      Finding all prime numbers up to a given limit
 * </p>
 * <p>
 *      Iteratively marking all multiples of a given prime number as composite (not prime).
 *      Once all multiples of a prime number have been marked,
 *      the prime number itself is added to the list of prime numbers.
 *      The algorithm then repeats this process with the next unmarked prime number.
 * </p>
 */
public class EratosthenesSieve {

    public static void main(String[] args) {

        // 0. create a row of numbers (in this example, from 2 to 1000) [because 1 is not a prime number]
        LinkedList<Integer> inputRow =
                IntStream.rangeClosed(2, 1000)
                        .boxed()
                        .collect(Collectors.toCollection(LinkedList::new));

        // 1. create a row of numbers starting from 2 to the floored square root of the biggest number in the initial row
        // (for the 2 - 1000 row will result a stream containing 2 - 31)

        // (we know that If a number is divisible by a prime factor, then it must also be divisible by the square of that prime factor)
        // (In other words, if a number is not divisible by any prime factor up to its square root,
        // then it cannot be divisible by any larger prime factor, and therefore it must be prime.)

        // 2. for each of these factors we do a check in the input row:
        //  each input row number is divided by the factor
        //  and if the rest of the division is 0 but the number checked is different from the factor
        // then that number is not prime and will be removed from the inputRow
        // In the end we will remain with a row made of prime numbers (only divisible by 1 and itself)
        IntStream.rangeClosed(2, Double.valueOf(Math.sqrt(inputRow.getLast())).intValue())
                .forEach(factor -> inputRow.removeIf(num -> num % factor == 0 && num != factor));




        Integer[] array = inputRow.toArray(Integer[]::new);
        System.out.println(Arrays.toString(array));
    }
}