package sec11a_packages;

/*
File -> Project Structure -> Artifacts -> select Jar -> Select From Modules With Dependencies
Press + to create an artifact
after
Build -> Build Artifacts = to create a jar that can be used as an external library later


to import jar to the external library:
File -> Project Structure -> Libraries
Press + then Java to import the jar file to the external library

*/

// Create a suitably named package containing a class called Series
// with the following static methods:
//
// nSum(int n) returns the sum of all numbers from 0 to n. The first 10 numbers are:
// 0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55.
//
// factorial(int n) returns the product of all numbers from 1 to n
//      i.e. 1 * 2 * 3 * 4 ... * (n - 1) * n.
// The first 10 factorials are:
// 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800.
//
// fibonacci(n) returns the nth Fibonacci number. These are defined as:
// f(0) = 0
// f(1) = 1
// f(n) = f(n-1) + f(n-2)
// (so f(2) is also 1. The first 10 fibonacci numbers are:
// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55.
//
// When you have tested your functions, delete the Example1 class and
// produce a jar file.
//
// Create a new project and add your Series library, then test the
// three methods in the main() method of your new project.


public class Main {
    public static void main(String[] args) {

        System.out.println("sum of all numbers from 0 to n (0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55)");
        for (int i = 0; i <= 10; i++) {
            System.out.println(Series.nSum(i));
        }

        System.out.println("product of all numbers from 1 to n (1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800)");
        for (int i = 1; i <= 10; i++) {
            System.out.println(Series.factorial(i));
        }

        System.out.println("nth Fibonacci number (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)");
        for (int i = 0; i <= 10; i++) {
            System.out.println(Series.fibonacci(i));
        }

        System.out.println("nth Fibonacci number (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)");
        for (int i = 0; i <= 10; i++) {
            System.out.println(Series.fibonacciRecursive(i));
        }
    }

}
