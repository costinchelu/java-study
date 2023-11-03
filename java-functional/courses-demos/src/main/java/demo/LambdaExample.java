package demo;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExample {

   public static void main(String[] args) {

      List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

      // anonymous class:
      Collections.sort(names, new Comparator<String>() {

         @Override
         public int compare(String a, String b) {
            return b.compareTo(a);
         }
      });

      // using lambda
      Collections.sort(names, (String a, String b) -> {
         return b.compareTo(a);
      });

      // or
      Collections.sort(names, (String a, String b) -> b.compareTo(a));

      // shorter:
      Collections.sort(names, (a, b) -> b.compareTo(a));

      Collections.sort(names, Comparator.reverseOrder());

   }
}

/**
Each howto.stream_api.lambda corresponds to a given type, specified by an interface.
A so called functional interface must contain exactly one abstract method declaration.
Each howto.stream_api.lambda expression of that type will be matched to this abstract method.
Since default methods are not abstract you're free to add default methods to your functional interface.
 */
class SecondExample {

   public static void main(String[] args) {

      // =============== implementation
//      Converter<String, Integer> integerConverter = (f) -> Integer.valueOf(f);
      Converter<String, Integer> integerConverter = Integer::valueOf;

      // =============== call
      Integer converted = integerConverter.convert("123");
      System.out.println(converted);

      // =============== accessing local variables
      final int num = 1;
      Converter<Integer, String> stringConverter = from -> String.valueOf(from + num);
      System.out.println(stringConverter.convert(2));
   }
}

/**
 * We can use arbitrary interfaces as howto.stream_api.lambda expressions as long as the
 * interface only contains one abstract method. To ensure that your interface
 * meet the requirements, you should add the @FunctionalInterface annotation.
 * The compiler is aware of this annotation and throws a compiler error as soon
 * as you try to add a second abstract method declaration to the interface.
 */
@FunctionalInterface
interface Converter<F, T> {
   T convert(F from);
}

/**
 * In contrast to local variables we have both read and write access to instance fields
and static variables from within howto.stream_api.lambda expressions.
This behaviour is well known from anonymous objects. */
class LambdaThirdExample {

   static int outerStaticNumber;

   int outerNumber;

   void testScopes() {
      Converter<Integer, String> stringConverter1 = (from) -> {
         outerNumber = 23;
         return String.valueOf(from);
      };

      Converter<Integer, String> stringConverter2 = (from) -> {
        outerStaticNumber = 72;
        return String.valueOf(from);
      };
   }
}