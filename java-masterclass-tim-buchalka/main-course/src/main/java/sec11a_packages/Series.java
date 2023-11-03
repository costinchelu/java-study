package sec11a_packages;

public class Series {

    public static long nSum(int n) {
        long sum = 0;
        for(int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static long nSumGauss(int n) {
        return (n * (n + 1)) / 2;
    }

    public static long factorial(int n) {
        long product = 1;
        for(int i = 2; i <= n; i++) {
            product *= i;
        }
        return product;
    }

    public static long fibonacci(int n) {

        // Array to store Fibonacci numbers. Array size is (n + 1) because we will store 0th Fibonacci number too:
        long fib[] = new long[n + 1];

        //0th Fibonacci number is 0 and 1st is 1
        fib[0] = 0;

        if(n > 0) {
            fib[1] = 1;

            for(int i = 2; i <= n; i++) {
                //adding the previous 2 numbers in the series and storing them in the array:

                fib[i] = fib[i - 2] + fib[i - 1];
            }
        }
        return fib[n];
    }

    public static long fibonacciRecursive(int n) {
        if(n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
    }
}
