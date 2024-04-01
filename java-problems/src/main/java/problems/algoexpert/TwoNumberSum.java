package problems.algoexpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Input - array of distinct integers & an integer representing a target sum
 * <br>
 * If any two numbers in the input array, sum up the target sum, the function
 * should return them
 */
public class TwoNumberSum {

    public static void main(String[] args) {
        int[] array = {3, 5, 4, 6, 7, -2, 1, 9, 10, 8, 2, -4, -8, 11, 14, -1, -6};
        int targetSum = 10;
        List<Integer[]> result = solution2(array, targetSum);
        result.forEach(a -> System.out.println(Arrays.toString(a)));
    }

    /**
     * Time complexity: O(n^2)
     * <br>
     * Space complexity = O(n)
     */
    private static List<Integer[]> solution1(int[] array, int targetSum) {
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < array.length - 1; i++) {
            int firstNum = array[i];
            for (int j = i + 1; j < array.length; j++) {
                int secondNum = array[j];
                if (firstNum + secondNum == targetSum) {
                    Integer[] temp = new Integer[2];
                    temp[0] = firstNum;
                    temp[1] = secondNum;
                    result.add(temp);
                    }
                }
            }
        return result;
    }

    /**
     * Time complexity: O(n)
     * <br>
     * Space complexity = O(n)
     */
    private static List<Integer[]> solution2(int[] array, int targetSum) {
        List<Integer[]> result = new ArrayList<>();
        HashMap<Integer, Boolean> nums = new HashMap<>();
        for (Integer num: array) {
            int potentialMatch = targetSum - num;
            if (nums.containsKey(potentialMatch)) {
                Integer[] temp = new Integer[2];
                temp[0] = num;
                temp[1] = potentialMatch;
                result.add(temp);
                nums.remove(potentialMatch);
            } else {
                nums.put(num, true);
            }
        }
        return result;
    }

    /**
     * Time complexity: O(n log(n))
     * <br>
     * [because we are sorting the array which is nlog(n) then we iterate over the array which is n]
     * <br>
     * Space complexity = O(n)
     */
    private static List<Integer[]> solution3(int[] array, int targetSum) {
        List<Integer[]> result = new ArrayList<>();
        Arrays.sort(array);
        int leftPivot = 0;
        int rightPivot = array.length - 1;
        while (leftPivot < rightPivot) {
            int currentSum = array[leftPivot] + array[rightPivot];
            if (currentSum == targetSum) {
                Integer[] temp = new Integer[2];
                temp[0] = array[leftPivot++];
                temp[1] = array[rightPivot--];
                result.add(temp);
            } else if (currentSum < targetSum) {
                leftPivot++;
            } else {
                rightPivot--;
            }
        }
        return result;
    }
}
