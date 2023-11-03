package howto;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*

abc…	    Letters
123…	    Digits
\d	        Any Digit
\D	        Any Non-digit character
.	        Any Character
\.	        Period
[abc]	    Only a, b, or c
[^abc]	    Not a, b, nor c
[a-z]	    Characters a to z
[0-9]	    Numbers 0 to 9
\w	        Any Alphanumeric character
\W	        Any Non-alphanumeric character
{m}	m       Repetitions
{m,n}	    m to n Repetitions
*	        Zero or more repetitions
+	        One or more repetitions
?	        Optional character
\s	        Any Whitespace
\S	        Any Non-whitespace character
^…$	        Starts and ends
(…)	        Capture Group
(a(bc))	    Capture Sub-group
(.*)	    Capture all
(abc|def)   Matches abc or def

 */
public class RegexMatcher {

   public static void main(String[] args) {
      example1();
      matchADateString();
      capturingGroups();
      replacingStrings();
   }

   private static void example1() {
      final String regexExpression = "[Ll]orem";
      final String textToCheck = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus in condimentum massa. "
            + "Pellentesque quis accumsan ligula. Vivamus eu nisl est. Ut convallis dui quam, "
            + "quis finibus odio molestie eu. Nulla vitae urna congue, elementum massa ultrices, "
            + "euismod magna. Vivamus eleifend egestas mattis. Cras a tempor velit. Suspendisse tempor enim "
            + "vel metus ullamcorper molestie.\nSed ultrices nisl non lacus ultrices elementum. Praesent fringilla, "
            + "tellus quis aliquet eleifend, ex erat sollicitudin metus, et tristique neque arcu non arcu. "
            + "Suspendisse sit amet arcu eleifend, lorem lobortis metus vitae, laoreet arcu. Suspendisse tellus lorem, "
            + "tincidunt vitae dictum ut, lacinia eget lorem libero. Pellentesque habitant morbi tristique senectus et "
            + "netus et malesuada fames ac turpis egestas.";

      final Pattern pattern = Pattern.compile(regexExpression, Pattern.MULTILINE);
      final Matcher matcher = pattern.matcher(textToCheck);

      while (matcher.find()) {
         System.out.println("Full match: " + matcher.group(0));

         for (int i = 1; i <= matcher.groupCount(); i++) {
            System.out.println("Group " + i + ": " + matcher.group(i));
         }
      }

      Map<String, String> regexMap = getStringMap();
      regexMap.forEach((key, value) -> System.out.println(key + " : " + value));
   }

   private static Map<String, String> getStringMap() {
      Map<String, String> regexMap = new HashMap<>();
      regexMap.put("Case insensitive operator for 'Lorem' or 'lorem'", "(?i)lorem");
      regexMap.put("Match a non printable character like new line", "\n");
      regexMap.put("Matches any character in the square brackets. For special howto.regex characters, we need escape",
            "[Ll]orem");
      regexMap.put("^ Will invert matching in the square bracket when it is placed at the beginning of the bracket",
            "[^Ll]orem");
      regexMap.put("Any character including line break", ".");
      return regexMap;
   }

   private static void matchADateString() {
      // Let's use a regular expression to match a date string.
      Pattern pattern = Pattern.compile("([a-zA-Z]+) (\\d+)");
      Matcher matcher = pattern.matcher("June 24");
      if (matcher.matches()) {
         // Indeed, the expression "([a-zA-Z]+) (\d+)" matches the date string

         // To get the indices of the match, you can read the Matcher object's start and end values.
         // This will print [0, 7], since it matches at the beginning and end of the string
         System.out.println("Match at index [" + matcher.start() + ", " + matcher.end() + ")");

         // To get the fully matched text, you can read the Matcher object's group. This will print "June 24"
         System.out.println("Match: " + matcher.group());
      }
   }

   private static void capturingGroups() {
      // Let's use a regular expression to capture data from a few date strings.
      String pattern = "([a-zA-Z]+) (\\d+)";
      Pattern ptrn = Pattern.compile("([a-zA-Z]+) (\\d+)");
      Matcher matcher = ptrn.matcher("June 24, August 9, Dec 12");

      // This will print each of the matches and the index in the input string where
      // the match was found:
      // June 24 at index [0, 7)
      // August 9 at index [9, 17)
      // Dec 12 at index [19, 25)
      while (matcher.find()) {
         System.out
               .println(String.format("Match: %s at index [%d, %d]", matcher.group(), matcher.start(), matcher.end()));
      }

      // If we are iterating over the groups in the match again, first reset the
      // matcher to start at the beginning of the input string.
      matcher.reset();

      // For each match, we can extract the captured information by reading the captured groups.
      while (matcher.find()) {
         // This will print the number of captured groups in this match
         System.out.println(String.format("%d groups captured", matcher.groupCount()));

         // This will print the month and day of each match. Remember that the
         // first group is always the whole matched text, so the month starts at index 1 instead.
         System.out.println("Month: " + matcher.group(1) + ", Day: " + matcher.group(2));

         // Each group in the match also has a start and end index, which is the
         // index in the input string that the group was found.
         System.out.println(String.format("Month found at[%d, %d)", matcher.start(1), matcher.end(1)));
      }
   }

   private static void replacingStrings() {
      // Let's try and reverse the order of the day and month in a few date
      // strings. Notice how the replacement string also contains metacharacters
      // (the back references to the captured groups) so we use a verbatim string for that as well.
      Pattern pattern = Pattern.compile("([a-zA-Z]+) (\\d+)");
      Matcher matcher = pattern.matcher("June 24, August 9, Dec 12");

      // This will reorder the string inline and print:
      // 24 of June, 9 of August, 12 of Dec
      // Remember that the first group is always the full matched text,
      // so the month and day indices start from 1 instead of zero.
      String replacedString = matcher.replaceAll("$2 of $1");
      System.out.println(replacedString);

      /*
      When compiling a Pattern, you will notice that you can pass in additional flags
      to change how input strings are matched. Most of the available flags are a convenience
      and can be written into the regular expression itself directly,
      but some can be useful in certain cases.

      Pattern.CASE_INSENSITIVE
      makes the pattern case-insensitive so that it matches strings of different capitalization

      Pattern.MULTILINE
      is necessary if your input string has newline characters (\n)
      and allows the start and end metacharacter (^ and $ respectively) to match
      at the beginning and end of each line instead of at the beginning and end of the whole input string

      Pattern.DOTALL
      allows the dot metacharacter (.) to match new line characters as well

      Pattern.LITERAL
      makes the pattern literal, in the sense that the escaped characters are matched as-is.
      For example, the pattern "\d" will match a backslash followed by a 'd' character
      as opposed to a digit character
       */
   }
}
