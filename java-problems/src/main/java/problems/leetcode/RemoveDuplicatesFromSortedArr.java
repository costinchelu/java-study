package problems.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
<p>
 Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 <p>
 Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially.
 The remaining elements of nums are not important as well as the size of nums.
 Return k.
 <p>
 Input: nums = [1,1,2]<p>
 Output: 2, nums = [1,2,_]<p>
 Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.<p>
 It does not matter what you leave beyond the returned k (hence they are underscores).<p>
 <p>
 Input: nums = [0,0,1,1,1,2,2,3,3,4]<p>
 Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]<p>
 Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.<p>
 It does not matter what you leave beyond the returned k (hence they are underscores).<p>
 <p>
 Constraints:<p>
 <p>
 1 <= nums.length <= 3 * 104<p>
 -100 <= nums[i] <= 100<p>
 nums is sorted in non-decreasing order.
 */
public class RemoveDuplicatesFromSortedArr {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArr test = new RemoveDuplicatesFromSortedArr();
        System.out.println(test.removeDuplicates3(new int[] {1,1,2}));
        System.out.println(test.removeDuplicates3(new int[] {0,0,1,1,1,2,2,3,3,4}));
    }

    private int removeDuplicates(int[] nums) {
        return (int) Arrays.stream(nums).distinct().boxed().count();
    }

    // if the problem asks that nums should be changed so that first elements are distinct:
    private int removeDuplicates2(int[] nums) {
        int buffer = nums[0];
        List<Integer> distinct = new ArrayList<>();
        distinct.add(buffer);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] != nums[i]) {
                buffer = nums[i + 1];
                distinct.add(buffer);
            }
        }
        for (int i = 0; i < distinct.size(); i++) {
            nums[i] = distinct.get(i);
        }
        return distinct.size();
    }

    // better solution
    private int removeDuplicates3(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}
