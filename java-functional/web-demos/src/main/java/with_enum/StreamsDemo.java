package with_enum;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamsDemo {

   public static void main(String[] args) {

      // To get enum using it's value
      System.out.println(EnumCodes.getCodeName("2"));

      // findFirst
      List<Integer> list = Arrays.asList(1, 6, 10, 3, 7, 5);
      int a = list.stream().filter(x -> x > 6).findFirst().orElse(0);
      System.out.println(a);

      // findFirst vs findAny
      List<String> lst1 = Arrays.asList("John", "David", "Jack", "Duke", "Jill", "Dany", "Julia", "Jen", "Divya");
      List<String> lst2 = Arrays.asList("John", "David", "Jack", "Duke", "Jill", "Dany", "Julia", "Jen", "Divya");

      Optional<String> findFirst = lst1.parallelStream().filter(s -> s.startsWith("D")).findFirst();
      Optional<String> findAny = lst2.parallelStream().filter(s -> s.startsWith("J")).findAny();

      System.out.println(findFirst.orElse("D")); // Always print David
      System.out.println(findAny.orElse("J")); // Print Jack/Jill/Julia :behavior of this operation is explicitly nondeterministic
   }
}
