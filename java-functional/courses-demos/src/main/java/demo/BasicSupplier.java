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

        Supplier<String> stringSupplier = () -> "HI!";

        System.out.println(stringSupplier.get());  // HI!
        System.out.println(stringSupplier);  //demo.BasicSupplier$$Lambda/0x0000020218003200@4e50df2e ($$ means that the class doesn’t exist in a classfile on the file system. It exists only in memory)

        List<String> stringList = Arrays.asList(/*"a", "b", "c", "d"*/);
        System.out.println(stringList.stream()
                            .findAny()
                            .orElse("HI Y'ALL!"));
    }
}
