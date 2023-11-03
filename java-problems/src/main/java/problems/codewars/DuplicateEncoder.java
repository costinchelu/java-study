package problems.codewars;

/*
JAVA
Duplicate Encoder
6 kyu


Description:
The goal of this exercise is to convert a string to a new string where each character in the new string
is "(" if that character appears only once in the original string, or ")" if that character appears more than once
in the original string.
Ignore capitalization when determining if a character is a duplicate.

Examples
"din"      =>  "((("
"recede"   =>  "()()()"
"Success"  =>  ")())())"
"(( @"     =>  "))(("

 */

public class DuplicateEncoder {

    static String encode(String word){
        StringBuilder result = new StringBuilder("");
        String[] arr = word.split("");
        boolean marked = false;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if (arr[i].equalsIgnoreCase(arr[j]) && i != j) {
                    //result.append(")");
                    marked = true;
                }
            }
            if(marked) {
                result.append(")");
                marked = false;
            }
            else {
                result.append("(");
            }
        }
        return result.toString();
    }
}
