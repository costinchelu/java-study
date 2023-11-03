package problems.coderbyte;

public class FirstNonRepeatingCharInAString {

    public static void main(String[] args) {
        System.out.println(getFirstNotRepeatedChar("abcd"));
        System.out.println(getFirstNotRepeatedChar("abad"));
        System.out.println(getFirstNotRepeatedChar("abcb"));
        System.out.println(getFirstNotRepeatedChar("abab"));
        System.out.println(getFirstNotRepeatedChar("abababcab"));
    }

    public static String getFirstNotRepeatedChar(String str) {

        for(Character ch: str.toCharArray()) {
            if(str.indexOf(ch) == str.lastIndexOf(ch)) {
                return ch.toString();
            }
        }
        return "All chars are repeating at least once!";
    }
}
