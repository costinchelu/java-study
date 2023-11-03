package howto;

public class WorkWithTernaryOperators {

    public static void main(String[] args) {

        int a = 7;
        int b = 4;
        int c = 6;

        // simple ternary
        int max = (a > b) ? a : b;

        // complex ternary
        int min = (a < b) ? (a < c) ? a : c : (b < c) ? b : c;

        System.out.println("max = " + max);
        System.out.println("min = " + min);


        System.out.format("\nnumber %s", (a % 2 != 0) ? "odd" : "even");

    }
}
