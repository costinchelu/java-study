package problems.luxcity;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two words (start and end), and a word list,
 * find the length of shortest transformation sequence
 * from start to end, such that:
 *
 *      Only one letter can be changed at a time.
 *      Each transformed word must exist in the word list.
 *
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume start  and end are non-empty and are not the same.
 * In the input:
 *
 * start - string
 * end - string
 * words - array of strings
 *
 *  At the output: number
 *
 *  Example:
 *
 * start = "cat",
 * end = "zog",
 * words = ["dog", "gag", "zag", "pat", "rat", "rag",  "zog"]
 *
 * getResult( start, end, words ) --> 5
 *  As one shortest transformation is
 *
 * 'cat' -> 'rat' -> 'rag' -> 'zag' -> 'zog'
 */
class ShortestTransformSeq {

    public static int getResult(String start, String end, List<String> words) {
        if(!words.contains(end)) {
            return 0;
        }
        char[] chStart = start.toCharArray();
        char[] chEnd = end.toCharArray();
        int result = 0;
        boolean noChange;
        do {
            noChange = true;
            for (String word : words) {
                char[] chWord = word.toCharArray();
                int increment = 0;
                for (int i = 0; i < chWord.length; i++) {
                    if (chWord[i] != chStart[i]) {
                        increment++;
                    }
                }
                if (increment == 1) {
                    result++;
                    noChange = false;

                    char[] tempStart = new char[chWord.length];
                    System.arraycopy(chWord, 0, tempStart, 0, chWord.length);
                    chStart = tempStart;

                    increment = 0;
                    for (int i = 0; i < chStart.length; i++) {
                        if (chStart[i] != chEnd[i]) {
                            increment++;
                        }
                    }
                    if (increment == 1) {
                        result++;
                        return result;
                    }
                }
            }
        } while (!noChange);
        return result;
    }

    public static void main(String[] args) {
        String[] strArr = {"dog", "gag", "zag", "pat", "rat", "rag",  "zog"};
        String start = "cat";
        String end = "zog";
        List<String> words = new ArrayList<>();
        for(int i = 0; i < strArr.length; i++) {
            words.add(strArr[i]);
        }
        System.out.println(getResult(start, end, words));
    }
}
