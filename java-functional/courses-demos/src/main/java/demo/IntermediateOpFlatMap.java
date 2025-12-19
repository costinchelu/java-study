package demo;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@code public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)}
 */
public class IntermediateOpFlatMap {

    public static void main(String[] args) {
        List<String> titles = List.of("Spring", "Spring Boot", "Stream API", "Microservices", "Docker", "Linux", "Python", "React", "Java 8");

        // form a list letters made from the stream:
        List<String> listOfLetters = titles
                .stream()
                .map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .collect(toList());

        List<String> distinctSortedLetters = titles
                .stream()
                .map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .toList();

        // convert the list to an array of letters
        String[] letterArr = titles
                .stream()
                .map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .toArray(String[]::new);

        System.out.println("---------------------");

        List<Integer> numbers1 = List.of(1, 2, 3);
        List<Integer> numbers2 = List.of(3, 4);

        List<int[]> pairs = numbers1.stream().
                flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .toList();

        pairs.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}
