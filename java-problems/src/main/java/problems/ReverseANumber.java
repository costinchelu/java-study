package problems;


public class ReverseANumber {

    public static void main(String[] args) {

        int result1 = mathWhile(258);
        System.out.println("Using mathematical implementation with while loop: " + result1);
        int result2 = mathFor(258);
        System.out.println("Using mathematical implementation with for loop: " + result2);
        int result3 = recursiveWrapper(258);
        System.out.println("Using recursion: " + result3);
    }

    private static int mathWhile(int number) {
        int reversed = 0;
        int toReverse = Math.abs(number);

        while (toReverse > 0) {
            int mod = toReverse % 10;
            reversed = reversed * 10 + mod;
            toReverse /= 10;
        }

        return number < 0 ? reversed * -1 : reversed;
    }

    private static int mathFor(int number) {
        int reversed = 0;
        int toReverse = Math.abs(number);

        for (; toReverse > 0; toReverse /= 10) {
            int mod = toReverse % 10;
            reversed = reversed * 10 + mod;
        }

        return number < 0 ? reversed * -1 : reversed;
    }

    private static int recursiveWrapper(int number) {
        int output = recursiveRev(Math.abs(number), 0);
        return number < 0 ? output * -1 : output;
    }

    private static int recursiveRev(int toReverse, int recursiveReversedNumber) {
        if (toReverse > 0) {
            int mod = toReverse % 10;
            recursiveReversedNumber = recursiveReversedNumber * 10 + mod;
            toReverse /= 10;
            return recursiveRev(toReverse, recursiveReversedNumber);
        }

        return recursiveReversedNumber;
    }
}
