package problems.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 121
 * <p>
 * Output: true
 * <p>
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: x = -121
 * <p>
 * Output: false
 * <p>
 * Explanation: From left to right, it reads -121. From right to left, it
 * becomes 121-. Therefore, it is not a palindrome.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: x = 10
 * <p>
 * Output: false
 * <p>
 * Explanation: Reads 01 from right to left. Therefore, it is not a palindrome.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= x <= 231 - 1
 */
public class PalindromeNumber {

   public static void main(String[] args) {

   }

   // O(log10(x))
   private boolean isPalindrome(int x) {
      if (x < 0) {
         return false;
      } else if (x == 0) {
         return true;
      }
      List<Integer> list = new ArrayList<>();
      while (x != 0) {
         list.add(x % 10);
         x /= 10;
      }
      for (int i = 0; i <= list.size() / 2; i++) {
         if (!Objects.equals(list.get(i), list.get(list.size() - 1 - i))) {
            return false;
         }
      }
      return true;
   }
}
