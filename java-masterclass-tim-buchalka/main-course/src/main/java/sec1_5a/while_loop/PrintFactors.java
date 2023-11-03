package sec1_5a.while_loop;

    /*
Write a method named printFactors with one parameter of type int named number.
If number is < 1, the method should print "Invalid Value".
The method should print all factors of the number.

EXAMPLE INPUT/OUTPUT:

* printFactors(6); → should print 1 2 3 6
* printFactors(32); → should print 1 2 4 8 16 32
* printFactors(10); → should print 1 2 5 10
* printFactors(-1); → should print "Invalid Value" since number is < 1

HINT: Use a while or for loop.

NOTE: When printing numbers, each number can be in its own line.
They don't have to be separated by a space.
For example, the printout for printFactors(10); can be:
1
2
5
10
*/

public class PrintFactors {

    public static void printFactors(int number)
    {
        if(number < 1) System.out.println("Invalid Value");
        for(int i = 1; i <= number; i++)
        {
            if (number % i == 0) System.out.println(i);
        }
    }
}
