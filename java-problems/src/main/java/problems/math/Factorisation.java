package problems.math;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

// find out the smallest factors
public class Factorisation {

    public static void main(String[] args) {
//        int[] result = IntStream.of(10, 87, 97, 43, 121, 20)
//                .flatMap(Factorisation::factorize)
//                .distinct()
//                .sorted()
//                .toArray();
//        System.out.println(Arrays.toString(result));

        System.out.println(factorize(20));
    }

//    private static IntStream factorize1(int value) {
//        List<Integer> factors = new ArrayList<>();
//        for (int i = 2; i <= value; i++) {
//            while (value % i == 0) {
//                factors.add(i);
//                value /= i;
//            }
//        }
//        return factors.stream().mapToInt(Integer::intValue);
//    }

    private static List<Integer> factorize(int input) {
        // we can use a set instead of list if we only want to find out distinct factors
        List<Integer> factors = new ArrayList<>();
//        Set<Integer> factors = new TreeSet<>();
        for (int i = 2; i <= input; i++) {
            while (input % i == 0) {
                factors.add(i);
                input /= i;
            }
        }
        return factors;
    }
}
