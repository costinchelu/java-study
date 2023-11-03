package problems.coderbyte;
/*
Determine the missing digit replaced by "x" in a equation type expression.
all the numbers and operations are separated by space:
"10 - 2 = x"
"5x0 * 3 = 1500"
 */


public class MissingDigitFromEquation {
    public static String MissingDigit(String str) {
        String[] split = str.split(" ");

        int xPosition = -1;
        if (split[0].contains("x")) {
            xPosition = 0;
        } else if (split[2].contains("x")) {
            xPosition = 2;
        } else {
            xPosition = 4;
        }

        int operationWX = 0;
        switch (split[1]) {
            case "+":
                if (xPosition == 0) {
                    operationWX = Integer.parseInt(split[4]) - Integer.parseInt(split[2]);
                } else if (xPosition == 2) {
                    operationWX = Integer.parseInt(split[4]) - Integer.parseInt(split[0]);
                } else {
                    operationWX = Integer.parseInt(split[0]) + Integer.parseInt(split[2]);
                }
                break;
            case "-":
                if (xPosition == 0) {
                    operationWX = Integer.parseInt(split[4]) + Integer.parseInt(split[2]);
                } else if (xPosition == 2) {
                    operationWX = Integer.parseInt(split[0]) - Integer.parseInt(split[4]);
                } else {
                    operationWX = Integer.parseInt(split[0]) - Integer.parseInt(split[2]);
                }
                break;
            case "*":
                if (xPosition == 0) {
                    operationWX = Integer.parseInt(split[4]) / Integer.parseInt(split[2]);
                } else if (xPosition == 2)  {
                    operationWX = Integer.parseInt(split[4]) / Integer.parseInt(split[0]);
                } else {
                    operationWX = Integer.parseInt(split[0]) * Integer.parseInt(split[2]);
                }
                break;
            case "/":
                if (xPosition == 0) {
                    operationWX = Integer.parseInt(split[4]) * Integer.parseInt(split[2]);
                } else if (xPosition == 2)  {
                    operationWX = Integer.parseInt(split[0]) / Integer.parseInt(split[4]);
                } else {
                    operationWX = Integer.parseInt(split[0]) / Integer.parseInt(split[2]);
                }
                break;
        }

        String op = String.valueOf(operationWX);
        int indexOfX = split[xPosition].indexOf("x");

        return String.valueOf(op.charAt(indexOfX));
    }

    public static void main (String[] args) {
//    Scanner s = new Scanner(System.in);
//    System.out.println("Enter: ");
//
//    System.out.print(MissingDigit(s.nextLine()));

        System.out.println(MissingDigit("1x5 + 55 = 160"));
        System.out.println(MissingDigit("105 - 5x = 50"));
        System.out.println(MissingDigit("105 - 55 = 5x"));
        System.out.println(MissingDigit("1x5 * 5 = 525"));
        System.out.println(MissingDigit("105 / x = 21"));
        System.out.println(MissingDigit("105 / 5 = x1"));
    }
}
