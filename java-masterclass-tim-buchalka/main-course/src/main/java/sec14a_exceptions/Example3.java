package sec14a_exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Example3 {
    public static void main(String[] args) {
        try{
            int result = divide();
            System.out.println(result);
        }
        catch(ArithmeticException e) {
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    /*alternatively, we can mve both exception handling in main:

    public static void main(String[] args) {
        try{
            int result = divide();
            System.out.println(result);
        }
        catch(ArithmeticException | NoSuchElementException e) {
            System.out.println(e.toString());
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    */
    private static int divide() {
        int x, y;
        try{
            x = getInt();
            y = getInt();
            System.out.println(" x = " + x + ", y = " + y);
            return x / y;
        }
        catch(NoSuchElementException e) {
            throw new ArithmeticException("no suitable input");     // will only be thrown if ctrl+D is pressed
            // ctrl+D sends all of the current line that you've type to the program (even if you've written anything)
        }
        catch (ArithmeticException e){
            throw new ArithmeticException("attempt to divide by zero");     //will be thrown if y = 0
        }
    }

     private static int getInt() {
         Scanner s = new Scanner(System.in);
         System.out.println("Please enter an integer:");
         while(true) {
             try{
                 return s.nextInt();
             }
             catch(InputMismatchException e) {
                 // go round again. Read past the end of line in the input first
                 s.nextLine();      //start fresh
                 System.out.println("Please enter a number using only the digits 0 to 9");
             }
         }
     }




}
