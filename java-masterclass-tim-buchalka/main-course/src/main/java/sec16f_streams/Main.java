package sec16f_streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G53", "G49", "G60", "g59", "G50",
                "I26", "I17", "I29",
                "O71");

        // using just lambda to populate a list, sort and print it
        /*
        List<String> gNumbers = new ArrayList<>();

        someBingoNumbers.forEach(number -> {
            if(number.toUpperCase.startsWith("G")) {
                gNumbers.add(number);
            }
        });

        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
        gNumbers.forEach((String s) -> System.out.println(s));
         */



        // using streams to do the same thing:
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);
        // a stream is a sequence of elements supporting sequential and parallel aggregate operations

        // another example (add all elements from the stream to a list of Strings):
        List<String> gNumbersSorted = new ArrayList<>();
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(gNumbersSorted::add);


        // create a stream of objects:
        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        Stream<String> concatStreams = Stream.concat(ioNumberStream, inNumberStream);
        // System.out.println(concatStreams.count());  // it contains duplicate numbers
        System.out.println("-----------using peek (debugging)-------------------");
        System.out.println(concatStreams
                .distinct()
                .peek(System.out::println)
                .count());  // no duplicates


        // using collect to get G numbers
        System.out.println("-----------using collect-------------------");
        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());

        for(String s : sortedGNumbers) {
            System.out.println(s);
        }
    }
}
