package howto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *   #           Omit position if no digit exists for it.<br>
 *   0           Put 0 in position if no digit exists for it.
 */
public class StringFormatting {

    public static void main(String[] args) {
        double d = 1234.567;
        NumberFormat f1 = new DecimalFormat("###,###,###.0");
        System.out.println(f1.format(d)); // 1,234.6

        NumberFormat f2 = new DecimalFormat("000,000,000.00000");
        System.out.println(f2.format(d)); // 000,001,234.56700

        NumberFormat f3 = new DecimalFormat("Your Balance $#,###,###.##");
        System.out.println(f3.format(d)); // Your Balance $1,234.57


        LocalDate date = LocalDate.of(2022, Month.OCTOBER, 20);
        System.out.println(date.getDayOfWeek()); // THURSDAY
        System.out.println(date.getMonth()); // OCTOBER
        System.out.println(date.getYear()); // 2022
        System.out.println(date.getDayOfYear()); // 293

        LocalDate date2 = LocalDate.of(2022, Month.OCTOBER, 20);
        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dt = LocalDateTime.of(date2, time);
        System.out.println(date2.format(DateTimeFormatter.ISO_LOCAL_DATE));   // 2022-10-20
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));    // 11:12:34
        System.out.println(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2022-10-20T11:12:34

        var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(dt.format(f)); // October 20, 2022 at 11:12

        var dateTime = LocalDateTime.of(2022, Month.OCTOBER, 20, 6, 15, 30);
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        System.out.println(dateTime.format(formatter)); // 10/20/2022 06:15:30
        System.out.println(formatter.format(dateTime)); // 10/20/2022 06:15:30

        var f4 = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(dt.format(f4)); // October 20, 2022 at 06:15

        // formatter - escaping single quote
        var g1 = DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm");
        System.out.println(dt.format(g1)); // October 20, Party's at 06:15
        var g2 = DateTimeFormatter.ofPattern("'System format, hh:mm: 'hh:mm");
        System.out.println(dt.format(g2)); // System format, hh:mm: 06:15
        var g3 = DateTimeFormatter.ofPattern("'NEW! 'yyyy', yay!'");
        System.out.println(dt.format(g3)); // NEW! 2022, yay!
    }
}
