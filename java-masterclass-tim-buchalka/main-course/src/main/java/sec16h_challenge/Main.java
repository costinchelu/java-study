package sec16h_challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // CHALLENGE 1 ________________

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this into an array";
                String[] parts = myString.split(" ");
                for(String part : parts) {
                    System.out.println(part);
                }
            }
        };

        Runnable runnable2 = () -> {
            String myString = "Let's split this into an array";
            String[] parts = myString.split(" ");
            for(String part : parts) {
                System.out.println(part);
            }
        };

        // CHALLENGE 2 ________________

        Function<String, String> challenge2Lambda = (source) -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        // CHALLENGE 3 ________________

        System.out.println(challenge2Lambda.apply("1234567890"));

        // CHALLENGE 5 ________________

        String result = challenge4Method(challenge2Lambda, "1234567890");

        // CHALLENGE 6 ________________

        Supplier<String> iLoveJava = () -> "I love Java!";

        // CHALLENGE 7 ________________

        String supplierResult = iLoveJava.get();

        //  CHALLENGE 9 ________________

        List<String> topNames2015 = Arrays.asList(
                "Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack", "Charlie", "harry", "Jacob"
        );

        {
            List<String> firstUpperCaseList = new ArrayList<>();
            topNames2015.forEach(name -> firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
            // firstUpperCaseList.sort((s1, s2) -> s1.compareTo(s2));
            // firstUpperCaseList.forEach(s -> System.out.println(s));

            //  CHALLENGE 10 ________________

            firstUpperCaseList.sort(String::compareTo);
            firstUpperCaseList.forEach(System.out::println);
        }

        //  CHALLENGE 11 ________________

        {
            topNames2015
                    .stream()
                    .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                    .sorted(String::compareTo)
                    .forEach(System.out::println);
        }

        //  CHALLENGE 12 ________________

        long namesBeginningWithA = topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();

        //  CHALLENGE 13-14 ________________

        topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());

    }

    // CHALLENGE 2
    public static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for(int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    // CHALLENGE 4
    public static String challenge4Method(Function<String, String> func, String source) {
        return func.apply(source);
    }
}
