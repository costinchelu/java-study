package problems.math;

import java.util.Locale;

public class Power {

    public static double raiseToPower(double a, int b) {
        double result = 1d;
        while (b > 0) {
            result *= a;
            b--;
        }
        return result;
    }

    // Compute the largest power of 2 less than or equal to n:
    public static long largestPower(long n) {
        long power = 1;
        long result = 1;
        while(result <= n / 2) {
            result *= 2;
            System.out.println("2 ^ " + power + " = " + result);
            power++;
        }
        return power - 1;
    }

    public static void main(String[] args) {
        double number = 5.5;
        int power = 6;

        System.out.println("Result is: " + raiseToPower(number, power));
        System.out.format(Locale.ENGLISH, "Rounded result: %.2f%n", raiseToPower(number, power));
    }
}
