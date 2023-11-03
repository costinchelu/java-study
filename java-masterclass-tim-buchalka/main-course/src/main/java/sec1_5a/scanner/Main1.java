package sec1_5a.scanner;

// 				Parsing values from a string
//				Reading User Input


import java.util.Scanner;

public class Main1 {
    public static void main(String[] args)
    {
        String numberAsString = "2019";
        System.out.println("numberAsString = " + numberAsString);

        // clasa Integer
        // functia parse merge si la doublw (Double.parseDouble)
        int number = Integer.parseInt(numberAsString);
        System.out.println("Number = " + number);

        number += 1;   //rezulta 2020
        numberAsString += 1;    // rezulta 20191 (concateneaza 1 la 2019 (existent))

        System.out.println("numberAsString = " + numberAsString);
        System.out.println("number = " + number);

        //

        // obiect nou al clasei Scanner (java utils)
        Scanner scanner = new Scanner(System.in);       //new instance

        System.out.println("Enter your year of birth: ");

        boolean hasNextInt = scanner.hasNextInt();  //checks to see if user input integers
        if(hasNextInt)
        {
            int yearOfBirth = scanner.nextInt();
            scanner.nextLine(); // to handle the next line character (enter after age input)

            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            int age = 2018 - yearOfBirth;
            if(age >= 0 & age <= 125)
            {
                System.out.println("Your name is " + name + ". Your age is " + age + ".");
            }
            else
            {
                System.out.println("Wrong age.");
            }
        }
        else System.out.println("Not a valid number.");

        scanner.close();    // clean the instance
    }
}
