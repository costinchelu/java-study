package howto;

import java.util.function.BiFunction;

public class Switch {

    enum CardType {SILVER, GOLD, PLATINUM, CORPORATE, DIAMOND}

    public static void main(String[] args) {
        classicSwitchStatement();

        var func = switchExpression(CardType.DIAMOND);
        Double result = func.apply(1.5, 1);
        System.out.println("Result is: " + result);
    }

    private static void classicSwitchStatement() {
        int month = 8;
        String monthString;
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        System.out.println(monthString);
    }

    private static BiFunction<Double, Integer, Double> switchExpression(CardType cardType) {
        return switch (cardType) {
            case SILVER -> (a, b) -> (a * 0) + b;
            case GOLD -> (a, b) -> (a * .05) + b;
            case PLATINUM, CORPORATE -> (a, b) -> (a * 0.1) + b * 2;
            case DIAMOND -> (a, b) -> (a * 0.15) + b * 3;
        };
    }

    private static StringBuilder switchWithStreams(StringBuilder sb, String segment) {
        segment.chars()
                .mapToObj(c -> (char) c)
                .map(c -> switch(c){
                    case '+' -> "~2";
                    case '-' -> "~9";
                    case '*' -> "~5";
                    default -> String.valueOf(c);
                })
                .forEach(sb::append);
        return sb;
    }

}
