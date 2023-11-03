package sec12b_collections_2_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetMain {
    public static void main(String[] args) {

        System.out.println("\nSEPARATE SETS");
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        for(int i = 1; i <= 100; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }
        System.out.println("There are " + squares.size() + " squares and " + cubes.size());

        //
        System.out.println("\nUNION (OPERATION)");
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("Union contains " + union.size() + " elements.");

        System.out.println("\nINTERSECTION (OPERATION)");
        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);
        System.out.println("Intersection contains " + intersection.size() + " elements.");
        for(int i : intersection) {
            System.out.println(i + " is the square of " + (int)Math.sqrt(i) + " and the cube of " + (int)Math.cbrt(i));
        }

        System.out.println("\naddAll METHOD (SIMPLE METHOD TO TRANSFER ELEMENTS OF AN ARRAY TO A COLLECTION)");
        Set<String> words = new HashSet<>();
        String sentence = "one day in the year of the fox";
        String[] arrayWords = sentence.split(" ");
        words.addAll(Arrays.asList(arrayWords));
        for(String s : words) {
            System.out.println(s);
        }

        System.out.println("\nASYMMETRIC DIFFERENCE (ONE SET - INTERSECTION BETWEEN BOTH SETS)");
        Set<String> nature = new HashSet<>();
        String[] natureWords = {"all", "nature", "is", "but", "art", "unknown", "to", "thee"};
        nature.addAll(Arrays.asList(natureWords));
        Set<String> divine = new HashSet<>();
        String[] divineWords = {"to", "err", "is", "human", "to", "forgive", "divine"};
        divine.addAll(Arrays.asList(divineWords));

        System.out.println("nature - divine:");
        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine);
        printSet(diff1);

        System.out.println("\ndivine - nature");
        Set<String> diff2 = new HashSet<>(divine);
        diff2.removeAll(nature);
        printSet(diff2);

        System.out.println("\nSYMMETRIC DIFFERENCE (UNION OF BOTH SETS - INTERSECTION BETWEEN BOTH SETS");
        Set<String> union2 = new HashSet<>(nature);
        union2.addAll(divine);
        Set<String> intersection2 = new HashSet<>(nature);
        intersection2.retainAll(divine);
        union2.removeAll(intersection2);
        printSet(union2);


        System.out.println("\ncontainsAll METHOD (TESTING FOR SUBSETS)");
        if(nature.containsAll(divine)) {
            System.out.println("divine is a subset of nature");
        }
        if(nature.containsAll(intersection2)) {
            System.out.println("intersection2 is a subset of nature");
        }
        if(divine.containsAll(intersection2)) {
            System.out.println("intersection2 is a subset of divine");
        }
    }

    private static void printSet(Set<String> set) {
        for(String s : set) {
            System.out.printf(s + " ");
        }
        System.out.println();
    }
}
