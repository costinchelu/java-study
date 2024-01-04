package problems.math;

import java.util.Locale;

public class Power {

    public static double raiseToPower(double number, int power) {
        double result = 1D;
        while (power > 0) {
            result *= number;
            power--;
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
