package demo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BasicBiPredicateBiConsumerBiFunction {

    public static void main(String[] args) {

        BiPredicate<Integer, String> biPredicate = (number, string) -> number > 5 && string.length() > 5;
        System.out.println(biPredicate.test(7, "blabla"));

        BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length() + str2.length();
        System.out.println(biFunction.apply("example 1", "example 2"));

        BiConsumer<Integer, String> biConsumer = consumeIntStr();
        biConsumer.accept(15, "text");
    }

    private static BiConsumer<Integer, String> consumeIntStr() {
        return (inputInt, inputStr) -> {
            System.out.println("--biconsumer-");
            System.out.println(inputInt);
            System.out.println(inputStr);
            System.out.println("--biconsumer-");
        };
    }
}
