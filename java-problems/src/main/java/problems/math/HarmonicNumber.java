package problems.math;

public class HarmonicNumber {

    // Harmonic number (sum of the series like 1/1 + 1/2 + 1/3 + ... + 1/n)
    public static double harmonic(long n) {
        double sum = 0.0;
        for (long i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }
}
