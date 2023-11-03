package problems.codewars;


/*
JAVA
Descending Order
7 kyu


Description:
Your task is to make a function that can take any non-negative integer as a argument and return it
with its digits in descending order.
Essentially, rearrange the digits to create the highest possible number.

Examples:
Input: 21445 Output: 54421
Input: 145263 Output: 654321
Input: 1254859723 Output: 9875543221

 */

import java.util.ArrayList;

public class DescendingOrder {

    public static int sortDesc(final int num) {
        int n = num;
        if(num > 0) {
            ArrayList<Integer> numArr = new ArrayList<>();
            while (n != 0) {
                numArr.add(n%10);
                n /= 10;
            }
            while(!numArr.isEmpty()) {
                int max = 0;
                for(int i = 0; i < numArr.size(); i++) {
                    if(max < numArr.get(i)) {
                        max = numArr.get(i);
                    }
                }
                n = n * 10 + max;
                numArr.remove(numArr.lastIndexOf(max));
            }
            return n;
        }
        else {
            return 0;
        }
    }
}
