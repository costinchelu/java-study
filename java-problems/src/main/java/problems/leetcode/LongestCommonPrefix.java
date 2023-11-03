package problems.leetcode;

/**
 * Write a function to find the longest common prefix string amongst an array of
 * strings. If there is no common prefix, return an empty string "".
 * <p>
 * Example: Input: strs = ["flower","flow","flight"] Output: "fl"
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 200 0 <= strs[i].length <= 200 strs[i] consists of only
 * lowercase English letters.
 */
public class LongestCommonPrefix {

   public static void main(String[] args) {
      LongestCommonPrefix test = new LongestCommonPrefix();
      String[] test1 = { "flower", "flight", "flow" };
      String[] test2 = { "flower", "flow", "flight" };
      String[] test3 = { "dog", "racecar", "car" };
      String[] test4 = { "a" };
      String s = test.longestCommonPrefix(test1);
      String s2 = test.longestCommonPrefix(test2);
      String s3 = test.longestCommonPrefix(test3);
      String s4 = test.longestCommonPrefix(test4);
      System.out.println(s);
      System.out.println(s2);
      System.out.println(s3);
      System.out.println(s4);
   }

   private String longestCommonPrefix(String[] strs) {
      String prefix = "";
      for (int i = strs[0].length(); i >= 0; i--) {
         String check = strs[0].substring(0, i);
         for (String str : strs) {
            if (!str.startsWith(check)) {
               prefix = "";
               break;
            } else {
               prefix = check;
            }
         }
         if (!prefix.isBlank()) {
            return prefix;
         }
      }
      return "";
   }

   private String longestCommonPrefix2(String[] strs) {
      StringBuilder rv = new StringBuilder();
      int i = 0;
      while (i < strs[0].length()) {
         char c = strs[0].charAt(i);
         boolean matches = true;
         for (int j = 1; j < strs.length; j++) {
            if (i >= strs[j].length() || strs[j].charAt(i) != c) {
               matches = false;
               break;
            }
         }
         if (matches) {
            rv.append(c);
         } else {
            break;
         }
         i++;
      }
      return rv.toString();
   }
}
