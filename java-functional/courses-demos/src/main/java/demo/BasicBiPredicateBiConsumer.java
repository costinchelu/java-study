package demo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BasicBiPredicateBiConsumer {

    public static void main(String[] args) {

        BiPredicate<Integer, String> biPredicate = (number, string) -> number > 5 && string.length() > 5;

        System.out.println(biPredicate.test(7, "blabla"));

        BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length() + str2.length();

        System.out.println(biFunction.apply("example 1", "example 2"));

        BiConsumer<Integer, String> biConsumer = getIntegerStringBiConsumer();

        biConsumer.accept(15, "some text");
    }

    private static BiConsumer<Integer, String> getIntegerStringBiConsumer() {
        return (s1, s2) -> {
            System.out.println("-------------------");
            System.out.println(s1);
            System.out.println(s2);
            System.out.println("-------------------");
        };
    }
}
