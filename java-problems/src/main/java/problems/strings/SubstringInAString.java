package problems.strings;

public class SubstringInAString {

    public static boolean foundString(String wholeExpression, String stringToLookFor) {
        char[] wholeChars = wholeExpression.toCharArray();
        char[] lookChars = stringToLookFor.toCharArray();
        boolean found;

        for (int i = 0; i < wholeChars.length; i++) {
            if (wholeChars[i] == lookChars[0]) {
                found = true;
                for(int j = i, k = 0; k < lookChars.length; j++, k++) {
                    if (wholeChars[j] != lookChars[k]) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(foundString("Astha", "st"));
        System.out.println(foundString("Astha", "ste"));
        System.out.println(foundString("Asthathor", "tho"));
        System.out.println(foundString("Asthothar", "tho"));
    }
}
