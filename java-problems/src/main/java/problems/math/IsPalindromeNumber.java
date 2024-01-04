package problems.math;

public class IsPalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(145854488));
        System.out.println(isPalindrome(1458541));
    }

    public static boolean isPalindrome(int input) {
        int reversed = 0;
        int original = input;
        while (input > 0) {
            int digit = input % 10;
            reversed = reversed * 10 + digit;
            input = input / 10;
        }
        return reversed == original;
    }
}
