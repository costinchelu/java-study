package sec16d_functional_interfaces;

import java.util.Random;
import java.util.function.Supplier;

public class ExampleOfSupplier {

    // Example of Supplier
    public static void main(String[] args) {
        Random random = new Random();

        for(int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(100));
        }

        // functional method
        Supplier<Integer> randomSupplier = () -> random.nextInt(100);
        for(int i = 0; i < 10; i++) {
            System.out.println(randomSupplier.get());
        }
    }
}
