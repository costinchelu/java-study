package sec18e_challenge;

public class Utilities {

    // Returns a char array containing every nth char. When sourceArray.length < n, returns sourceArray

    public char[] everyNthChar(char[] sourceArray, int n) {

        if (sourceArray == null || sourceArray.length < n) {
            return sourceArray;
        }

        int returnedLength = sourceArray.length / n;
        char[] result = new char[returnedLength];
        int index = 0;

        for (int i = n - 1; i < sourceArray.length; i += n) {
            result[index++] = sourceArray[i];
        }

        return result;
    }

    // Removes pairs of the same character that are next to each other, by removing one occurrence of the character.
    // "ABBCDEEF" -> "ABCDEF"
    // "ABCBDEEF" -> "ABCBDEF" (the two B's aren't next to each other, so they aren't removed)
    // "" -> ""

    public String removePairs(String source) {

        // If length is less than 2, there won't be any pairs. Also we need to insure that we won't get
        // an exception when we are dealing with null values (empty strings are not null btw)
        if (source == null || source.length() < 2) {
            return source;
        }

        StringBuilder sb = new StringBuilder();

        // iteration is simpler with an array
        char[] chArr = source.toCharArray();

        // old buggy code:
//        for (int i = 0; i < chArr.length; i++) {
//            if (chArr[i] != chArr[i + 1]) {
//                sb.append(chArr[i]);
//            }
//        }
        int i = 0;
        while (i < chArr.length) {
            // make sure we are not checking with an index out of bounds
            if (i < (chArr.length - 1) && chArr[i] != chArr[i + 1]) {
                sb.append(chArr[i]);
                i++;
            } else if (i < (chArr.length - 1) && chArr[i] == chArr[i + 1]) {
                i++;
            } else {
                // for the last character in the char array (which is passed by the other two conditionals)
                sb.append(chArr[i]);
                i++;
            }
        }

        return sb.toString();
    }

    // performs a conversion based on some internal
    // business rule
    public int converter(int a, int b) {
        return (a/b) + (a * 30) - 2;
    }

    public String nullIfOddLength(String source) {
        if (source.length() % 2 == 0) {
            return source;
        }

        return null;
    }
}
