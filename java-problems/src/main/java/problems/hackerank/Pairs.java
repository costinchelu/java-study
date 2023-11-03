package problems.hackerank;


import howto.Timeit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Pairs {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Timeit.code(() -> System.out.println(pairs(k, arr)));
        Timeit.code(() -> System.out.println(pairs2(k, arr)));
        Timeit.code(() -> System.out.println(pairs3(k, arr)));

        bufferedReader.close();
    }

    /*
     * Complete the 'pairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     *
     * Non-optimized solution
     */
    public static int pairs(int k, List<Integer> arr) {
        int result = 0;
        for (Integer a : arr) {
            for (Integer b : arr) {
                if (a + k == b) {
                    result++;
                }
            }
        }
        return result;
    }

    // slightly optimized solution
    public static int pairs2(int k, List<Integer> arr) {
        int result = 0;
        Collections.sort(arr);
        for (int a = 0; a < arr.size(); a++) {
            for (int b = a; b < arr.size(); b++) {
                if (arr.get(a) + k == arr.get(b)) {
                    result++;
                }
            }
        }
        return result;
    }

    // optimized solution
    public static int pairs3(int k, List<Integer> arr) {
        int result = 0;
        Collections.sort(arr);
        for (Integer a : arr) {
            if (Collections.binarySearch(arr, a + k) > 0) {
                result++;
            }
        }
        return result;
    }
}

/*
Given an array of integers and a target value (k), determine the number of pairs of array elements so that
first number in the pair + target will be equal to the second number.

Example
k = 1
arr = [1, 2, 3, 4]
n = 4

1+1=2
2+1=3
3+1=4

Input Format
The first line contains two space-separated integers  and , the size of  and the target value.
The second line contains  space-separated integers of the array .

Constraints
each integer  will be unique

Sample Input

STDIN       Function
-----       --------
5 2         arr[] size n = 5, k =2
1 5 3 4 2   arr = [1, 5, 3, 4, 2]

OUTPUT
3

 */