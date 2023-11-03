package algo;

import java.util.HashMap;
import java.util.Map;

public class Recursion {

    static Map<Integer, Integer> buffer = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("=OPERATION ORDER EXAMPLE=");

        recurseMethod(4);

        System.out.println("=FACTORIAL EXAMPLE=");

        System.out.println("Factorial of 8 is: " + factorial(8));

        System.out.println("=FIBONACCI EXAMPLE=");

        System.out.println("20th fibonacci number is: " + fibonacci(20));

        System.out.println("20th fibonacci number is: " + fibonacciMem(20));
    }

    private static void recurseMethod(int num) {
        if (num != 0) {
            System.out.println("hello " + num);
            recurseMethod(--num);
            System.out.println("remainder " + num);
        }
    }

    // linear recursion
    private static int factorial(int n) {
        if (n <= 1) return 1;
        else return factorial(n - 1) * n;
    }

    // non-linear recursion
    private static int fibonacci(int n) {
        if (n < 2)  return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // recursion with memoization
    private static int fibonacciMem(int n) {
        if (n < 2)  return n;
        if (buffer.containsKey(n)) return buffer.get(n);

        int result = fibonacciMem(n - 1) + fibonacciMem(n - 2);
        buffer.put(n, result);

        return result;
    }
}