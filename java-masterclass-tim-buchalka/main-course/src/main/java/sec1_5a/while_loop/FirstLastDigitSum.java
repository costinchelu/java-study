package sec1_5a.while_loop;

/*
Write a method named getEvenDigitSum with one parameter of type int called number.
The method should return the sum of the even digits within the number.
If the number is negative, the method should return -1 to indicate an invalid value.


EXAMPLE INPUT/OUTPUT:
* getEvenDigitSum(123456789); → should return 20 since 2 + 4 + 6 + 8 = 20
* getEvenDigitSum(252); → should return 4 since 2 + 2 = 4
* getEvenDigitSum(-22); → should return -1 since the number is negative

*/

public class FirstLastDigitSum
{
    public static int sumFirstAndLastDigit(int number)
    {
        int sum = 0;
        int temp = 0;

        if (number < 0) return -1;
        else if (number / 10 == 0) return (2 * number);
        else
        {
            sum += number % 10;
            number /= 10;
            while (number != 0)
            {
                temp = number % 10;
                number /= 10;
            }
            sum += temp;
        }
        return sum;
    }


    public static int getEvenDigitSum(int number)
    {
        int sum = 0;
        if (number < 0) return -1;
        else
        {
            while (number != 0)
            {
                if ((number % 10) % 2 == 0)
                {
                    sum += number % 10;
                }
                number /= 10;
            }
        }
        return sum;
    }
}