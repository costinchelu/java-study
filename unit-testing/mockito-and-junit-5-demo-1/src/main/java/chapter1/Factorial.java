package chapter1;

public class Factorial {

    public static long factorial(long number) {
        if (number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
