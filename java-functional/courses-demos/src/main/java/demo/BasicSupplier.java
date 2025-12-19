package demo;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 Supplier - no input - returns something (it's like factory pattern)
 */
public class BasicSupplier {

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

        Supplier<String> stringSupplier = () -> "HI!";

        System.out.println(stringSupplier.get());  // HI!
        System.out.println(stringSupplier);  //demo.BasicSupplier$$Lambda/0x0000020218003200@4e50df2e ($$ means that the class doesn’t exist in a classfile on the file system. It exists only in memory)

        /*"a", "b", "c", "d"*/
        List<String> stringList = List.of(/*"a", "b", "c", "d"*/);
        System.out.println(stringList.stream()
                .findAny()
                .orElse("HI Y'ALL!"));

    }
}
