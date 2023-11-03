package demo;

import java.util.function.DoubleUnaryOperator;

/**
Currying is a technique where a function f of two arguments (x and y, say) is seen
instead as a function g of one argument that returns a function also of one argument.
The value returned by the latter function is the same as the value of the original function,
that is, f(x,y) = (g(x))(y).
 <p>
Of course, this generalizes: you can curry a six-argument function to first take arguments
numbered 2, 4, and 6 returning a function taking argument 5, which returns a
function taking the remaining arguments, 1 and 3.
When some but fewer than the full complement of arguments have been passed, we often say the function is partially applied.
 */
public class Currying {

    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoEUR = curriedConverter(0.9114, 0);
        DoubleUnaryOperator convertKmToMi = curriedConverter(0.6214, 0);

        double eur = convertUSDtoEUR.applyAsDouble(300);
        double fahrenheit = convertCtoF.applyAsDouble(25);
        double miles = convertKmToMi.applyAsDouble(60);

        System.out.println(eur + " $ = 300 €");
        System.out.println(fahrenheit + " °F = 25 °C");
        System.out.println(miles + " miles = 60 km");
    }

    static double converter(double x, double f, double b) {
        return x * f + b;
    }

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return operand -> operand * f + b;
    }
}
