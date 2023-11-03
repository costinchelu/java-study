package problems.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Constraints:
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class RomanToInteger {

    public static void main(String[] args) {
        RomanToInteger test = new RomanToInteger();
        System.out.println("2" + " == " + test.optimizedRomanToInt("II"));
        System.out.println("5" + " == " + test.optimizedRomanToInt("V"));
        System.out.println("9" + " == " + test.optimizedRomanToInt("IX"));
        System.out.println("19" + " == " + test.optimizedRomanToInt("XIX"));
        System.out.println("51" + " == " + test.optimizedRomanToInt("LI"));
        System.out.println("109" + " == " + test.optimizedRomanToInt("CIX"));
        System.out.println("515" + " == " + test.optimizedRomanToInt("DXV"));
        System.out.println("1024" + " == " + test.optimizedRomanToInt("MXXIV"));
        System.out.println("2049" + " == " + test.optimizedRomanToInt("MMXLIX"));
        System.out.println("3999" + " == " + test.optimizedRomanToInt("MMMCMXCIX"));
    }

    /*
LC runtime efficient solution
The key intuition lies in the fact that in Roman numerals,
when a smaller value appears before a larger value, it represents subtraction,
while when a smaller value appears after or equal to a larger value, it represents addition.
 */
    private int optimizedRomanToInt(String s) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                result -= map.get(s.charAt(i));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    // sub optimal
    private int romanToInt(String s) {
        int result = 0;
        Map<String, Integer> romanNumeralMap = new LinkedHashMap<>();
        romanNumeralMap.put("IX", 9);
        romanNumeralMap.put("IV", 4);
        romanNumeralMap.put("XC", 90);
        romanNumeralMap.put("XL", 40);
        romanNumeralMap.put("CM", 900);
        romanNumeralMap.put("CD", 400);
        romanNumeralMap.put("I", 1);
        romanNumeralMap.put("V", 5);
        romanNumeralMap.put("X", 10);
        romanNumeralMap.put("L", 50);
        romanNumeralMap.put("C", 100);
        romanNumeralMap.put("D", 500);
        romanNumeralMap.put("M", 1000);

        while (!s.isBlank()) {
            for (Map.Entry<String, Integer> entry : romanNumeralMap.entrySet()) {
                if (s.contains(entry.getKey())) {
                    result += entry.getValue();
                    s = s.replaceFirst(entry.getKey(), " ");
                }
            }
        }
        return result;
    }

    // LC most runtime efficient solution
    private int optimizedRomanToInt2(String s) {
        char[] arr = s.toCharArray();
        int[] val = new int[s.length()];
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (arr[i]) {
                case 'I':
                    val[i] = 1;
                    break;
                case 'V':
                    val[i] = 5;
                    break;
                case 'X':
                    val[i] = 10;
                    break;
                case 'L':
                    val[i] = 50;
                    break;
                case 'C':
                    val[i] = 100;
                    break;
                case 'D':
                    val[i] = 500;
                    break;
                case 'M':
                    val[i] = 1000;
                    break;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            if (val[i] > val[i - 1]) {
                val[i] = val[i] - val[i - 1];
                val[i - 1] = 0;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            result = result + val[i];
        }

        return result;
    }
}