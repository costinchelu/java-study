package problems.luxcity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * In the input:        nums - array of integers
 *  At the output:      array of integers
 *
 *  Example:
 *
 * nums = [3, 5, 1, 4]
 * getResult( nums ) --> [20, 12, 60, 15]
 */

class ProductArray {

    public static List<Integer> getResult(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        for(Integer integer : nums) {
            int n = integer;
            int product = 1;
            for (Integer i: nums) {
                product *= i;
            }
            product /= n;
            result.add(product);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] arr = { 3, 5, 1, 4};
        List<Integer> nums = new ArrayList<>(Arrays.asList(arr));
        System.out.println(getResult(nums));
    }
}
