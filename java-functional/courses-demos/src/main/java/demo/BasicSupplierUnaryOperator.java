package demo;

import java.util.Random;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 Supplier - no input - returns something (it's like factory pattern)
 <p></p>
Unary operator - an operation on a single operand that produces a result of the same type as its operand.
This is a specialization of Function for the case where the operand and result are of the same type.
 */
public class BasicSupplierUnaryOperator {

    public static void main(String[] args) {

        Supplier<Integer> randomIntegerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };

        Supplier<Integer> randomIntegerSupplierImpl = new Supplier<Integer>() {
            @Override
            public Integer get() {
                Random random = new Random();
                return random.nextInt(1000);
            }
        };

        System.out.println(randomIntegerSupplierImpl.get());



        UnaryOperator<Integer> unaryOperator = x -> 3 * x;

        UnaryOperator<Integer> unaryOperatorImpl = new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x) {
                return 3 * x;
            }
        };

        System.out.println(unaryOperatorImpl.apply(10));
    }
}
