package problems.math;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Sieve of Eratosthenes / prime numbers
public class EratosthenesSieve {

    public static void main(String[] args) {

        LinkedList<Integer> nums = IntStream.rangeClosed(2, 1000).boxed().collect(Collectors.toCollection(LinkedList::new));

        IntStream
                .rangeClosed(2, Double.valueOf(Math.sqrt(nums.getLast())).intValue())
                .forEach(factor -> nums.removeIf(num -> num % factor == 0 && num != factor));

        nums.forEach(System.out::println);
    }
}