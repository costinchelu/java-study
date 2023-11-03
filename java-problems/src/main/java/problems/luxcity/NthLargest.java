package problems.luxcity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Find the n-th largest element in an unsorted array.
 * Note that it is the n-th largest element in the sorted order, not the n-th distinct element.
 *
 * In the input:
 *
 * nums - array of integers
 * n - integer, 1 ≤ n ≤ array's length.
 *  At the output: integer
 *
 *  Example:
 *
 * nums = [5,7,4,3,7,8,6]
 * n = 3
 * getResult( nums, n ) --> 7
 */
class NthLargest {

    public static int getResult(List<Integer> nums, int n) {
        Collections.sort(nums);
        return nums.get(nums.size() - n);
    }

    public static void main(String[] args) {
        Integer[] arr = {5,7,4,3,7,8,6};
        List<Integer> nums = Arrays.asList(arr);
        int n = 3;
        System.out.println(getResult(nums, n));
    }
}
