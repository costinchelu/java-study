package problems.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
* Count occurences of words in a string ignoring caps or other characters than literals
 */
public class CountWordOccurence {

   public static void main(String[] args) {

      String sir2 = "ada, bbb[] dddd; ada ccECc cCecc ada";

      HashMap<String, Integer> map = new HashMap<>();
      List<String> split3 =
              Arrays.stream(sir2.split(" ")).
                      map(str -> str.replaceAll("[^A-Za-z]+", "").toLowerCase(Locale.ROOT)).
                      collect(Collectors.toList());
      for (String s : split3) {
         map.put(s, 0);
      }
      for (String s : split3) {
         map.computeIfPresent(s, (key, value) -> value + 1);

         // or:
         // if (map.containsKey(s)) {
         // map.put(s, map.get(s) + 1);
         // }
      }
      System.out.println(map);
   }
}
