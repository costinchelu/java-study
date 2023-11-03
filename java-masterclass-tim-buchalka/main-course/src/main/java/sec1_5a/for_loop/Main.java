package sec1_5a.for_loop;

public class Main
{
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Dobanda de " + i + "% la 1000 lei = " +
                    String.format("%.2f", calculateInterestRate(1000, i)) + " lei");
        }

        int count = 0;
        for (int i = 1; i < 50; i++)
        {
            if(isPrimeEficienta(i))
            {
                count++;
                System.out.println("Number " + i + " is a prime number");
                if(count == 10)
                {
                    System.out.println("Iesire din bucla FOR!");
                    break;                  //utilizare break cand primele 10 numere prime sunt gasite
                }
            }
        }
    }
    // String.format() pentru a afisa doar primele 2 zecimale

    public static double calculateInterestRate(double amount, double interestRate)
    {
        return (amount * (interestRate/100));
    }

    public static boolean isPrime(int n)        // varianta clasica
    {
        if(n == 1)                      // 1 nu este prim
        {
            return false;
        }                               //orice nr se divide cu 1, deci se pleaca de la 2
        for(int i = 2; i <= n/2; i++)    //verificam doar numerele <= 1/2 din n
        {
            if(n % i == 0)              // daca nu are rest, inseamna ca e divizibil
            {
                return false;
            }
        }
        return true;        // daca nu este divizibil cu niciun i (unde i <= n/2) => nr. prim
    }

    public static boolean isPrimeEficienta(int n)        // varianta eficienta (cu radical)
    {
        if(n == 1)
        {
            return false;
        }
        for(int i = 2; i <= (long) Math.sqrt(n); i++)    //verificam doar numerele <= radical din n
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

}