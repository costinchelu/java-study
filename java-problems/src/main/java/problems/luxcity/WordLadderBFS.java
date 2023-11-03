package problems.luxcity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
class WordLadderBFS {

    public static int getResult(String start, String end, List<String> words) {
        if (!words.contains(end)) {
            return 0;
        }
        Set<String> wordSet = new LinkedHashSet<>(words);
        LinkedList<String> queue = new LinkedList<>();
        queue.add(start);
        int result = 1;
        while (!queue.isEmpty()) {
            boolean foundMatch = false;
            String word = queue.remove();

            if (word.equals(end)) {
                return result;
            }

            char[] wordCharArr = word.toCharArray();
            for (int i = 0; i < wordCharArr.length; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char tempChar = wordCharArr[i];
                    if (wordCharArr[i] != ch) {
                        wordCharArr[i] = ch;
                    }
                    String obtainedWord = new String(wordCharArr);
                    if (wordSet.contains(obtainedWord)) {
                        queue.add(obtainedWord);
                        wordSet.remove(obtainedWord);
                        if (!foundMatch) {
                            result++;
                            foundMatch = true;
                        }
                    }
                    wordCharArr[i] = tempChar;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] strArr = {"dog", "gag", "zag", "pat", "rat", "rag", "zog"};
        String start = "cat";
        String end = "zog";
        List<String> words = new ArrayList<>();
        for(int i = 0; i < strArr.length; i++) {
            words.add(strArr[i]);
        }
        System.out.println(getResult(start, end, words));
    }
}
