package sec14a_exceptions;

// an exception is an event that happens during execution of a program and disrupts the flow of the program

public class Example1 {
    public static void main(String[] args) {
        int x = 98;
        int y = 0;
        System.out.println(divideLBYL(x, y));
        System.out.println(divideEAFP(x, y));
        System.out.println(divide(x, y));
    }

    //LBYL = look before you leap
    private static int divideLBYL(int x, int y) {
        //if y would actually be a 0, it will produce Division by 0 error
        if(y != 0) {
            return x / y;
        }
        else {
            return 0;
        }
    }

    //EAFP = easy to ask for permission than forgiveness
    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        }
        catch(ArithmeticException e) {
            return 0;
        }
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}
