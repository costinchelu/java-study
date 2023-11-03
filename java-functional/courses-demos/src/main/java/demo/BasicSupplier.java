package demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BasicSupplier {

//    @Override
//    public String get() {
//        return "HI!";
//    }

    public static void main(String[] args) {

//        Supplier<String> supplierExample = () -> {return "HI!";};
        Supplier<String> supplierExample = () -> "HI!";

        System.out.println(supplierExample.get());

        List<String> stringList = Arrays.asList(/*"a", "b", "c", "d"*/);
        System.out.println(stringList.stream()
                            .findAny()
                            .orElse("HI!"));
    }
}
