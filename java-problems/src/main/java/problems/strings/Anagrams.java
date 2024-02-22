package problems.strings;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Anagrams {

    // O(n) = nlog(n) [double pivot quicksort average time complexity]
    private static boolean isAnagram(String a, String b) {
        char[] aArr = a.toLowerCase().toCharArray();
        char[] bArr = b.toLowerCase().toCharArray();
        if (aArr.length != bArr.length) {
            return false;
        } else {
            Arrays.sort(aArr);
            Arrays.sort(bArr);
            for (int i = 0; i < aArr.length; i++) {
                if (aArr[i] != bArr[i] || aArr[i] < 'a') {
                    return false;
                }
            }
            return true;
        }
    }

    // O(n) = n
    private static boolean isAnagramEfficient(String a, String b) {
        char[] aArr = a.toLowerCase().toCharArray();
        char[] bArr = b.toLowerCase().toCharArray();
        if (aArr.length != bArr.length) {
            return false;
        } else {
            Map<Character, Long> map = new HashMap<>();
            char letter = 'a';
            long[] primes = first26Primes();
            for (int i = 0; i < 26; i++) {
                map.put(letter, primes[i]);
                letter++;
            }
            long aProd = 1;
            long bProd = 1;

            for (int i = 0; i < aArr.length; i++) {
                aProd *= map.get(aArr[i]);
                bProd *= map.get(bArr[i]);
            }
            return aProd == bProd;
        }
    }

    private static boolean isAnagramEfficient2(String a, String b) {
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        if (arr1.length != arr2.length) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            Integer get1 = map1.getOrDefault(arr1[i], 0);
            map1.put(arr1[i], ++get1);
            Integer get2 = map2.getOrDefault(arr2[i], 0);
            map2.put(arr2[i], ++get2);
        }
        return map1.equals(map2);
    }

    private static long[] first26Primes() {
        return LongStream
                .iterate(2, i -> i + 1)
                .filter(i -> {
                    int sqrt = (int)Math.sqrt(i);
                    return IntStream.rangeClosed(2, sqrt).noneMatch(each -> i % sqrt == 0);
                })
                .limit(26)
                .toArray();
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("cart", "crat"));
        System.out.println(isAnagram("impossible", "return"));
        System.out.println(isAnagram("return", "turner"));
        System.out.println(isAnagram("today", "todai"));
        System.out.println();
        System.out.println(isAnagramEfficient("cart", "crat"));
        System.out.println(isAnagramEfficient("impossible", "return"));
        System.out.println(isAnagramEfficient("return", "turner"));
        System.out.println(isAnagramEfficient("today", "todai"));
        System.out.println();
        System.out.println(isAnagramEfficient2("cart", "crat"));
        System.out.println(isAnagramEfficient2("impossible", "return"));
        System.out.println(isAnagramEfficient2("return", "turner"));
        System.out.println(isAnagramEfficient2("today", "todai"));
    }
}
