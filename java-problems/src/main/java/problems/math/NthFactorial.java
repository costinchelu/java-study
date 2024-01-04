package problems.math;

import java.util.stream.LongStream;

public class NthFactorial {

   public static long factorialIterative(long n) {
      int accumulator = 1;
      if (n <= 1) return accumulator;
      while (n > 1) {
         accumulator *= n;
         n--;
      }
      return accumulator;
   }

   public static long factorialStreams(long n) {
      return LongStream
              .rangeClosed(1, n)
              .reduce(1, (long a, long b) -> a * b);
   }

   public static long factorialRecursive(long n) {
      return n == 1 ? 1 : n * factorialRecursive(n - 1);
   }

   /**
    * write a
    * recursive definition of factorial where the recursive call is the last thing that happens
    * in the function (we say the call is in a tail position). This different form of
    * recursion style can be optimized to run fast.
    * @param n n-th factorial
    * @return factorial of n
    */
   public static long factorialTailRecursive(long n) {
      return factorialHelper(1, n);
   }

   /**
    * the recursive call is the last thing that happens in the function.
    * By contrast in our previous definition of factorial-Recursive, the last thing was a multiplication of n
    * and the result of a recursive call.
    * This form of recursion is useful because instead of storing each intermediate result
    * of the recursion onto different stack frames, the compiler can decide to reuse a single stack frame.
    * Indeed, in the definition of factorialHelper, the intermediate results
    * (the partial results of the factorial) are passed directly as arguments to the function.
    * There’s no need to keep track of the intermediate result of each recursive call on a
    * separate stack frame—it’s accessible directly through the argument of the function.
    <p>
    * The bad news is that Java doesn’t support this kind of optimization. But adopting
    * tail recursion may be a better practice than classic recursion because it opens the way
    * to eventual compiler optimization.
    * @param acc accumulator
    * @param n n-th factorial
    * @return factorial of n
    */
   private static long factorialHelper(long acc, long n) {
      return n == 1 ? acc : factorialHelper(acc * n, n - 1);
   }
}
