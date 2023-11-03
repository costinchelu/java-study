package sec1_5a.scanner;

/*
Read the numbers from the console entered by the user and print the minimum
 and maximum number the user has entered.
-Before the user enters the number, print the message Enter number:
-If the user enters an invalid number, break out of the loop and print the
 minimum and maximum number.
*/

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int min = 0;
        int max = 0;
        boolean firstNumber = true;

        while(true)
        {
            System.out.println("Enter number: ");
            boolean isAnInt = scanner.hasNextInt();
            if(isAnInt)
            {
                int number = scanner.nextInt();
                if(firstNumber)     //activates only when firstNumber is true
                {
                    firstNumber = false;
                    min = number;
                    max = number;
                    //by changing firstNumber state to false, this will prevent further activation of
                    //the if check. Just after the input of the first number,
                    //min and max will take the value of number variable
                }
                if(number > max) max = number;
                if(number < min) min = number;
            }
            else break;
            scanner.nextLine();
        }
        System.out.println("Minimum = " + min);
        System.out.println("Maximum = " + max);
        scanner.close();

        //second version

        Scanner scanner2 = new Scanner(System.in);
        int minim = Integer.MAX_VALUE;
        int maxim = Integer.MIN_VALUE;


        while(true)
        {
            System.out.println("Enter number: ");
            boolean isAnInt = scanner2.hasNextInt();
            if(isAnInt)
            {
                int number = scanner2.nextInt();
                if(number > maxim) maxim = number;
                if(number < minim) minim = number;
            }
            else break;
            scanner2.nextLine();
        }
        System.out.println("Minimum = " + minim);
        System.out.println("Maximum = " + maxim);
        scanner2.close();

    }
}
