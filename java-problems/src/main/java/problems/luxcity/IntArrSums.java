package problems.luxcity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Let us consider this example (array written in general format):
 *
 * numbers = [4, 2, 1, 5]
 *
 * Its following parts:
 *
 * numbers = [4, 2, 1, 5]
 * numbers =    [2, 1, 5]
 * numbers =       [1, 5]
 * numbers =          [5]
 * numbers =           []
 *
 * The corresponding sums are (put together in a list): [12, 8, 6, 5, 0]
 *
 * The function getSums will take as parameter a list numbers
 * and return a list of the sums of its parts as defined above.
 *
 * In the input: numbers - list of integers
 *  At the output: list of integers
 */
class IntArrSums {

    public static List<Integer> getSums(List<Integer> numbers) {
        Integer[] result = new Integer[numbers.size() + 1];
        int sum = 0;
        for (Integer listInt: numbers) {
            sum += listInt;
        }
        Arrays.fill(result, sum);

        for (int i = result.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                result[i] -= numbers.get(j);
            }
        }
        return Arrays.asList(result);
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(2);
        numbers.add(1);
        numbers.add(5);
        System.out.println(getSums(numbers));
    }
}
