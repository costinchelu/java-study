package problems.leetcode;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

/**
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
<p>
You may assume that each input would have exactly one solution, and you may not use the same element twice.
<p>
You can return the answer in any order.
<p>
 Examples:
 <p>
Input: nums = [2,7,11,15], target = 9<p>
Output: [0,1]<p>
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].<p>
<p>
Input: nums = [3,2,4], target = 6<p>
Output: [1,2]<p>
<p>
Input: nums = [3,3], target = 6<p>
Output: [0,1]<p>
<p>
Constraints:<p>
<p>
2 <= nums.length <= 104<p>
-109 <= nums[i] <= 109<p>
-109 <= target <= 109<p>
Only one valid answer exists.<p>
 <p>
 SIMILAR TO hackerrank.Pairs
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {3, 2, 4};
        int[] nums3 = {3, 3};

        String result1 = Arrays.stream(twoSum(nums1, 9)).mapToObj(String::valueOf).collect(Collectors.joining(", ", "[ ", " ]"));
        String result2 = Arrays.stream(twoSum(nums2, 6)).mapToObj(String::valueOf).collect(Collectors.joining(", ", "[ ", " ]"));
        String result3 = Arrays.stream(twoSum(nums3, 6)).mapToObj(String::valueOf).collect(Collectors.joining(", ", "[ ", " ]"));
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);


    }

    // more efficient. O(n)
    public static int[] twoSum(int[] nums, int target) {
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for (int i = 0; i < nums.length; i++) {
            if (table.containsValue(target - nums[i])) {
                int val = -1;
                for (Map.Entry<Integer, Integer> e : table.entrySet()) {
                    if (e.getValue().equals(target - nums[i])) {
                        val = e.getKey();
                    }
                }
                return new int[] {val, i};
            } else {
                table.put(i, nums[i]);
            }
        }
        return new int[] {-1, -1};
    }


    // O(n^2) runtime
    public static int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
