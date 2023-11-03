package sec1_5a.while_loop;

/*
Write a method called isPalindrome with one int parameter called number.
The method needs to return a boolean.
It should return true if the number is a palindrome number otherwise it should return false.
Check the tips below for more info about palindromes.
Example Input/Output

isPalindrome(-1221); → should return true
isPalindrome(707); → should return true
isPalindrome(11212); → should return false because reverse is 21211 and that is not equal to 11212.

Tip: What is a Palindrome number?  A palindrome number is a number which when reversed is equal to the original number. For example: 121, 12321, 1001 etc.
Tip: Logic to check a palindrome number
Find the the reverse of the given number. Store it in some variable say reverse. Compare the number with reverse.
If both are the the same then the number is a palindrome otherwise it is not.
Tip: Logic to reverse a number
Declare and initialize another variable to store the reverse of a number, for example reverse = 0.
Extract the last digit of the given number by performing the modulo division (remainder).
Store the last digit to some variable say lastDigit = num % 10.
Increase the place value of reverse by one.
To increase place value multiply the reverse variable by 10 e.g. reverse = reverse * 10.
Add lastDigit to reverse.
Since the last digit of the number is processed, remove the last digit of num. To remove the last digit divide number by 10.
Repeat steps until number is not equal to (or greater than) zero.
A while loop would be good for this coding exercise.

Tip: Be careful with negative numbers. They can also be palindrome numbers.
Tip: Be careful with reversing a number, you will need a parameter for comparing a reversed number with the starting number (parameter).
NOTE: The method isPalindrome needs to be defined as public static like we have been doing
*/

public class Main {
    public static void main(String[] args) {
        int count = 1;
        while (count != 6) //sau     while(count < 6);
        {
            System.out.println(count);
            count++;
        }

        // o alta varianta:

        count = 1;
        while (true) {
            if (count == 6)          // test pentru iesirea din bucla perpetua
            {
                break;
            }
            System.out.println(count);
            count++;
        }

        count = 1;
        do {
            {
                System.out.println(count);
                count++;
            }
        } while (count != 6);

        System.out.println("*********");
        evenNumbers(6, 100);
    }

    public static boolean isEven(int number) {
        if (number % 2 == 0) return true;
        else return false;
    }

    public static void evenNumbers(int number, int finish) {
        int evenNumbersFound = 0;
        while (number <= finish) {
            number++;       //verifica de la (number + 1)
            if (!isEven(number)) {
                continue;       //utilizare continue (reincepe while)
            }
            System.out.println(number);
            evenNumbersFound++;
            if (evenNumbersFound >= 5) break;    //utilizare break (iese din while)
        }
    }
}
