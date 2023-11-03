package demo;

import java.util.Random;
import java.util.function.*;

public class BasicFunctionalInterfaces {

    private  static final int NO_1 = 5;

    private  static final int NO_2 = 7;

    public static void main(String[] args) {

        Predicate<Integer> predicate = x -> x % 2 == 0;
        //-> predicate -> returns boolean

        Consumer<Integer> consumer = System.out::println;
        //-> consumer -> receives a parameter and returns nothing

        Supplier<Integer> randomIntegerSupplier = BasicFunctionalInterfaces::supplyRandomInt;
        //-> supplier -> receives nothing and returns a result

        Function<Integer, Integer> function = x -> x * x;
        //-> function -> receive a parameter and returns a result

        UnaryOperator<Integer> unaryOperator = x -> 3 * x;
        //-> operator -> returns the type it receives as a parameter

        // bi versions of previous classes
        BiPredicate<Integer, String> biPredicate = (number, string) -> number > 5 && string.length() > 5;
        BiConsumer<Integer, String> biConsumer = (s1, s2) -> System.out.println(s1 + s2);
        BiFunction<Integer, Double, String> biFunction = (x, y) -> x + " " + y;
        BinaryOperator<Integer> binaryOperator = Integer::sum;

        // We can avoid using wrapper classes when primitives are required bi using primitive functions
        // java.util.function also have primitive functions like IntBinaryOperator:
        IntBinaryOperator intBinaryOperator = (int1, int2) -> int1 + int2;
        System.out.println(intBinaryOperator.applyAsInt(NO_1, NO_2));

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> j = f.compose(g);
        int composingResult = h.apply(1);
        int composingResult2 = j.apply(1);
        System.out.println(composingResult);
        System.out.println(composingResult2);
    }

    public static Integer supplyRandomInt() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
