package demo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * you can use teeing() to return multiple values of your own.
 */
public class CollectorsTeeing {

    public static void main(String[] args) {

        List<String> strings = List.of("x", "y", "z");

        Separations collect = strings.stream()
                .collect(Collectors.teeing(
                        Collectors.joining(" "),
                        Collectors.joining(","),
                        Separations::new
                ));
        System.out.println(collect); // Separations[spaceSeparated=x y z, commaSeparated=x,y,z]
    }
}

record Separations(String spaceSeparated, String commaSeparated) {}
