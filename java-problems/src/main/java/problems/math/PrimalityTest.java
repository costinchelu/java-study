package problems.math;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimalityTest {

    // also working with: for (int i = 2; i <= Math.sqrt(input); i++)
    public static boolean isPrimeIterative(long input) {
        if (input < 2) {
            return false;
        }
        for (long i = 2; i <= input / i; i++) {
            if (input % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFunctional(long input) {
        long nRoot = (long) Math.sqrt(input);
        return LongStream.rangeClosed(2, nRoot).noneMatch(each -> input % each == 0);
    }

    public static void main(String[] args) {
        System.out.println(isPrimeIterative(53982894593057L));
        System.out.println(isPrimeFunctional(53982894593057L));

        System.out.println(isPrimeIterative(53982894593058L));
        System.out.println(isPrimeFunctional(53982894593058L));

        String first30PrimeNumbers = primes(30).map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(first30PrimeNumbers);
    }

    private static Stream<Integer> primes(int firstPrimes) {
        return Stream.iterate(2, i -> i + 1)
                .filter(PrimalityTest::isPrimeFunctional)
                .limit(firstPrimes);
    }
}
