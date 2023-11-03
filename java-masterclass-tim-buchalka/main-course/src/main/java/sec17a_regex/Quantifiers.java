package sec17a_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quantifiers {

    public static void main(String[] args) {
        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        String secondString = "abcDeeeF12GhhabcDeeiiiijkl99z";
        String hasWhiteSpace = "I have blanks and\ta tab. And also a newline\n";

        // {someNumber} states how many times a character occurs
        System.out.println(alphanumeric.replaceAll("^abcDe{3}", "REP "));
        // same thing using + (+ means one or more)
        System.out.println(alphanumeric.replaceAll("^abcDe+", "REP "));
        // in the following case, we are searching for 2 to 5 e's
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}", "REP "));


        // * after some character states - replace any expression followed or not by that character. So even if
        // ^abcD is or isn't followed by one or more e's, it will replace them with REP
        System.out.println(alphanumeric.replaceAll("^abcDe*", "REP "));

        // we are searching for one h (hence we use the +) followed by zero or more i's (hence
        // we're using the star) followed by j
        System.out.println(alphanumeric.replaceAll("h+i*j", "REP "));

        //--- Pattern - Matcher

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub heading</h2>>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        // .* all characters + 0 or more (anything before and anything after)
        String h2Pattern = ".*<h2>.*";
        Pattern pattern = Pattern.compile(h2Pattern);
        //Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(htmlText);

        System.out.println(matcher.matches());  // returns true
        matcher.reset();    // resetting the matcher allows reusing

        Pattern pattern2 = Pattern.compile("<h2>");
        Matcher matcher2 = pattern2.matcher(htmlText);

        int count = 0;
        while(matcher2.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher2.start() + " to " + matcher2.end());
        }
        matcher2.reset();

        // working with groups (opening & closing tag and anything in between)
        // * greedy quantifier (keeps searching for more chars)
        // ? turns it to a lazy quantifier
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while(groupMatcher.find()) {
            System.out.println("Occurrence: " + groupMatcher.group(1));
        }


        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while(h2TextMatcher.find()) {
            System.out.println("Occurrence: " + h2TextMatcher.group(2));
        }
    }
}
