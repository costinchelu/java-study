package sec16d_functional_interfaces;

import java.util.function.IntPredicate;

public class IntPredicates {

    public static void main(String[] args) {
        // there are different types of predicates. In this case it can accept an integer to be tested
        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;

        System.out.println(greaterThan15.test(10));
        int a = 20;
        System.out.println(greaterThan15.test(a + 5));

        // we can also chain predicates (true if both predicates return true and false if one of them returns false)
        System.out.println(greaterThan15.and(lessThan100).test(a + 30));
        System.out.println(greaterThan15.and(lessThan100).test(a - 5));

    }
}
