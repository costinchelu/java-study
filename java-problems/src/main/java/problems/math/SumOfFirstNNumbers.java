package problems.math;

public class SumOfFirstNNumbers {

    // ITERATIVE: sum of the first n numbers
    public static long nSum(long n) {
        long sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // RECURSIVE: sum of the first n numbers
    public static long sum(long n) {
        if (n == 1) {
            return 1;
        } else {
            return n + sum(n - 1);
        }
    }

    // Sum of the first n numbers using Gauss formula
    public static long nSumGauss(long n) {
        return (n * (n + 1)) / 2;
    }
}
