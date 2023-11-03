package problems.math;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimalityTest {

    public static boolean isPrimeIterative(long n) {
        if (n < 2) {
            return false;
        }
        for (long i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFunctional(long n) {
        long nRoot = (long) Math.sqrt((double) n);
        return LongStream.rangeClosed(2, nRoot).noneMatch(each -> n % each == 0);
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
