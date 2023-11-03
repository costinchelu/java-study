package problems.math;

public class NthFibonacci {

    // Fibonacci n-th number using recursive method
    public static long recursiveFibonacci(long n) {
        if (n <= 2) {
            return n;
        } else {
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
        }
    }

    // Fibonacci n-th number using non-recursive method
    public static long fibonacci(int n) {

        // Array to store Fibonacci numbers.
        // Array size is (n + 1) because we
        // will store 0th Fibonacci number too:
        long[] fib = new long[n + 1];

        //0th Fibonacci number is 0 and 1st is 1
        fib[0] = 0;

        if(n > 0) {
            fib[1] = 1;

            for(int i = 2; i <= n; i++) {
                //adding the previous 2 numbers in the series
                // and storing them in the array:

                fib[i] = fib[i - 2] + fib[i - 1];
            }
        }
        return fib[n];
    }


    public static void main(String[] args) {
        for (long i = 0; i <= 20; i++) {
            System.out.println(i + ")   " + recursiveFibonacci(i));
        }
    }
}
