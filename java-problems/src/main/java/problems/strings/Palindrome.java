package problems.strings;

public class Palindrome {

   public boolean isPalindromeReverseTheString(String text) {
//      String lowercaseText = text.replaceAll("\\s+", "").toLowerCase();
//      char[] charArr = lowercaseText.toCharArray();

      char[] charArr = text.toCharArray();
      int N = charArr.length;
      StringBuilder reversedString = new StringBuilder();
      for (int i = N - 1; i >= 0; i--) {
         reversedString.append(charArr[i]);
      }
      return (reversedString.toString()).equals(text);
   }

   public static boolean isPalindrome(String s) {
      int N = s.length();
      for (int i = 0; i < N / 2; i++) {
         if (s.charAt(i) != s.charAt(N - 1 - i)) {
            return false;
         }
      }
      return true;
   }
}
