package problems.math;

public class GCDEuclidAlgorithm {

    public static void main(String[] args) {

        int a = 50;
        int b = 125;

       long result = gcd(a, b);
         if (result == 1) {
             System.out.println("\nPrime numbers between each other");
         } else {
             System.out.println("\nGreatest common divisor between " + a + " & " + b + " is " + result);
         }
    }

    public static long gcd(long p, long q) {
        if (q <= 0 || p <= 0) {
            return 0;
        }
        while (p != q) {
            if (p > q) {
                p -= q;
            } else {
                q -= p;
            }
        }
        return p;
    }

    /**
     * Compute the greatest common divisor of two nonnegative integers p and q as follows:
     * If q is 0, the answer is p. If not, divide p by q and take the remainder r.
     * @return the greatest common divisor of q and r
     */
    public static long gcdRecursive(long p, long q) {
        if (q == 0) {
            return p;
        }
        long r = p % q;
        return gcdRecursive(q, r);
    }
}
