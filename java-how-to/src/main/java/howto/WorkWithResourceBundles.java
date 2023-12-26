package howto;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class WorkWithResourceBundles {

    public static void main(String[] args) {
        var us = Locale.of("en", "US");
        var france = Locale.of("fr", "FR");
        printWelcomeMessage(us); // Hello, The zoo is open
        printWelcomeMessage(france); // Bonjour, Le zoo est ouvert


        Locale.setDefault(Locale.of("en", "US"));
        Locale reqLocale = Locale.of("en", "CA");
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", reqLocale);
        System.out.print(rb.getString("hello"));
        System.out.print(". ");
        System.out.print(rb.getString("name"));
        System.out.print(" ");
        System.out.print(rb.getString("open"));
        System.out.print(" ");
        System.out.print(rb.getString("visitors") + "\n");
        String helloByName = rb.getString("helloByName");
        System.out.println(MessageFormat.format(helloByName, "Tammy", "Henry"));
    }

    public static void printWelcomeMessage(Locale locale) {
        var rb = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(rb.getString("hello") + ", " + rb.getString("open"));

//        rb.keySet().stream()
//                .map(k -> k + ": " + rb.getString(k))
//                .forEach(System.out::println);
    }
}
