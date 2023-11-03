package howto;

import java.util.Arrays;


public class GenerateAFilledArray {

    public static void main(String[] args) {

        int [] ones = new int[10];
        Arrays.fill(ones, 1);

        int [] sequential = Arrays.copyOf(ones, ones.length);
        Arrays.parallelPrefix(sequential, Integer::sum);

        for (int n : sequential) {
            System.out.println(n);
        }
    }
}
