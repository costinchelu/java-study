package problems.luxcity;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of words, we may encode it by writing a reference string S and a list of indexes A.
 *
 * Then for each index, we will recover the word by reading from the reference
 * string from that index until we reach a "$" character.
 *
 * What is the length of the shortest reference string S possible that encodes the given words?
 *
 * In the input:  - array of strings
 * In the output: integer
 *
 * Example:
 *
 * Input: words = ["lime", "me", "salt"]
 * Output: 10
 * Explanation: S = "lime$salt$" and indexes = [0, 2, 5].
 *
 * Note:
 * Each word has only lowercase letters.
 */
class ShortestEncode {

    public static int shortEncoding(List<String> words) {
        Set<String> set = new LinkedHashSet<>(words);
        for (String string: words) {
            for (int i = 1; i < string.length(); i++)
                set.remove(string.substring(i));
        }
        int result = 0;
        for (String string: set)
            result += string.length() + 1;
        return result;
    }

    public static void main(String[] args) {
        String[] strArr = {"lime", "me", "salt", "alt", "alts"};
        List<String> strList = Arrays.asList(strArr);
        System.out.println(shortEncoding(strList));
    }
}
