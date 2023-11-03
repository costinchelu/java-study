package problems.codewars;

/*
JAVA
Sum of Odd Numbers
7 kyu


Given the triangle of consecutive odd numbers:

             1
          3     5
       7     9    11
   13    15    17    19
21    23    25    27    29
...


Calculate the row sums of this triangle from the row index (starting at index 1) e.g.:

rowSumOddNumbers(1); // 1
rowSumOddNumbers(2); // 3 + 5 = 8

* */


public class RowSumOddNumbers {

    public static int rowSumOddNumbers(int n) {
        if (n <= 0) {
            return 0;
        }
        int lastNumber = 0;
        int sum = 0;
        for(int i = n; i > 0; i--) {
            lastNumber += i - 1;
        }
        lastNumber *= 2;
        for(int i = 0; i < n; i++) {
            sum = sum + ((lastNumber - 1) + 2 * (i + 1));
        }
        return sum;
    }
}