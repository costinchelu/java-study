package howto;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;


public class Localisation {

    public static void main(String[] args) throws ParseException {
        Locale[] availableLocales = Locale.getAvailableLocales();
        System.out.println(Arrays.toString(availableLocales));

        Locale defaultLocale = Locale.getDefault();
        System.out.println(defaultLocale);

        Locale roLocale = Locale.forLanguageTag("ro-RO");
        Locale roLocale2 = Locale.of("ro", "RO");
        System.out.println(roLocale.getDisplayName());

        Locale localeForUS = new Locale.Builder().setRegion("US").setLanguage("en").build();

        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;
        var usNumberFormat = NumberFormat.getInstance(Locale.US);
        System.out.println(usNumberFormat.format(attendeesPerMonth)); // 266,666
        var gerNumberFormat = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(gerNumberFormat.format(attendeesPerMonth)); // 266.666
        var caNumberFormat = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(caNumberFormat.format(attendeesPerMonth)); // 266 666



        String s = "40.45";
        var en = NumberFormat.getInstance(Locale.US);
        System.out.println(en.parse(s)); // 40.45
        var fr = NumberFormat.getInstance(Locale.FRANCE);
        System.out.println(fr.parse(s)); // 40
        /*
        In the United States, a dot (.) is part of a number, and the number is parsed as you might
expect. France does not use a decimal point to separate numbers. Java parses it as a formatting
character, and it stops looking at the rest of the number. The lesson is to make sure that
you parse using the right locale!
         */


        String income = "$92,807.99";
        var cf = NumberFormat.getCurrencyInstance(Locale.US);
        double value = (Double) cf.parse(income);
        System.out.println(value); // 92807.99



        // for compact number format - SHORT style is used by default (with the current locale)
        var formatters = Stream.of(
                NumberFormat.getCompactNumberInstance(),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), NumberFormat.Style.LONG),
                NumberFormat.getCompactNumberInstance(Locale.GERMAN, NumberFormat.Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.GERMAN, NumberFormat.Style.LONG),
                NumberFormat.getNumberInstance(),
                NumberFormat.getNumberInstance(Locale.US));

        formatters
                .map(str -> str.format(7_123_456))
                .forEach(System.out::println);




        Locale.setDefault(Locale.of("en", "US"));
        var dt = LocalDateTime.of(2022, Month.OCTOBER, 20, 15, 12, 34);
        var italy = Locale.of("it", "IT");

        // 10/20/22 --- 20/10/22
        printDT(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT), dt, italy);
        // 3:12 PM --- 15:12
        printDT(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT), dt, italy);
        // 10/20/22, 3:12 PM --- 20/10/22, 15:12
        printDT(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT), dt, italy);




        var spainLocale = Locale.of("es", "ES");
        var usLocale = Locale.of("es", "ES");
        var money = 1.23;

         // Print with default locale
         Locale.setDefault(usLocale);
         printCurrency(spainLocale, money); // $1.23, Spanish

         // Print with selected locale display
         Locale.setDefault(Locale.Category.DISPLAY, spainLocale);
         printCurrency(spainLocale, money); // $1.23, español

         // Print with selected locale format
         Locale.setDefault(Locale.Category.FORMAT, spainLocale);
         printCurrency(spainLocale, money); // 1,23 €, español
    }

    public static void printDT(DateTimeFormatter dtf, LocalDateTime dateTime, Locale locale) {
        System.out.println(dtf.format(dateTime) + " --- " + dtf.withLocale(locale).format(dateTime));
    }

    public static void printCurrency(Locale locale, double money) {
        System.out.println(
                NumberFormat.getCurrencyInstance().format(money)
                        + ", " + locale.getDisplayLanguage());
    }
}
