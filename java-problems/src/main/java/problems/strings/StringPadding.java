package problems.strings;

import java.util.List;

public class StringPadding {

    public static void main(String[] args) {
        String STR1 = "123456";
        String STR2 = "12345";
        String STR3 = "1234567";

        int sizeOfTheReturnedString = 12;

        // JAVA 8
        // List<String> stringList = new ArrayList<>(Arrays.asList(STR1, STR2, STR3));

        // JAVA 9
        List<String> stringList = List.of(STR1, STR2, STR3);

        stringList.stream()
                  .map(str -> returnPaddedStringsToTheLeft(str, sizeOfTheReturnedString))
                  .forEach(System.out::println);
        stringList.stream()
                  .map(str -> returnPaddedStringsToTheRight(str, sizeOfTheReturnedString))
                  .forEach(System.out::println);
    }

    private static String returnPaddedStringsToTheLeft(String inputString, int sizeOfTheReturnedString) {
        return String.format("%1$" + sizeOfTheReturnedString + "s", inputString).replace(' ', '0');
    }

    private static String returnPaddedStringsToTheRight(String inputString, int sizeOfTheReturnedString) {
        return String.format("%1$-" + sizeOfTheReturnedString + "s", inputString).replace(' ', '0');
    }
}
