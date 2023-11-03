package problems.codewars;

import java.util.Arrays;

/*
CodeWars 7kyu - Anagram detection

An anagram is the result of rearranging the letters of a word to produce a new word (see wikipedia).

Note: anagrams are case insensitive

Complete the function to return true if the two arguments given are anagrams of each other; return false otherwise.

Examples
"foefet" is an anagram of "toffee"

"Buckethead" is an anagram of "DeathCubeK"
 */

public class Anagrams {

    public static boolean isAnagram(String test, String original) {
        if (test.length() <= 1 || original.length() <= 1 || test.trim().length() == 1 ||original.trim().length() == 1) {
            return false;
        }
        char[] firstArr = test.toLowerCase().toCharArray();
        char[] secondArr = original.toLowerCase().toCharArray();

        if (firstArr.length != secondArr.length) {
            return false;
        }

        for (int i = 0; i < firstArr.length; i++) {
            for(int j = 0; j < secondArr.length; j++) {
                if (firstArr[i] == secondArr[j]) {
                    firstArr[i] = '*';
                    secondArr[j] = '*';
                    break;
                }
            }
        }
        String str1 = Arrays.toString(firstArr);
        String str2 = Arrays.toString(secondArr);
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(Anagrams.isAnagram("afLamil", "lmiaalf"));
    }
}