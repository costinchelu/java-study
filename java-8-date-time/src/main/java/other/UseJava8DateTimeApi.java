package other;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previous;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class UseJava8DateTimeApi {

    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd-MMMMM-yyyy");
        }
    };

    public static void main(String[] args) {
        useOldDate();
        useLocalDate();
        useTemporalAdjuster();
        useDateFormatter();
        printRamadan();
    }

    private static void useOldDate() {
        Date date = new Date(114, 2, 18);
        System.out.println(date);

        System.out.println(formatters.get().format(date));  // 18-martie-2014

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.MARCH, 18);
        System.out.println(calendar);
        System.out.println(calendar.getTime().getYear());       // 114
        System.out.println(calendar.getTime().getMonth());      // 2
        System.out.println(calendar.getTime().getDay());        // 2
        System.out.println(calendar.getTime().getHours());      // 12
        System.out.println(calendar.getTime().getMinutes());    // 37
    }

    private static void useLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalDate now1 = LocalDate.now();

        int year = date.getYear();              // 2014
        Month month = date.getMonth();          // MARCH
        int day = date.getDayOfMonth();         // 18
        DayOfWeek dow = date.getDayOfWeek();    // TUESDAY
        int len = date.lengthOfMonth();         // 31 (days in March)
        boolean leap = date.isLeapYear();       // false (not a leap year)
        System.out.println(date);

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20); // 13:45:20
        int hour = time.getHour();              // 13
        int minute = time.getMinute();          // 45
        int second = time.getSecond();          // 20
        System.out.println(time);

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20); // 2014-03-18T13:45
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();    // 2014-03-18
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();    // 13:45:20
        System.out.println(time1);

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant instantNow = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
        Duration d2 = Duration.between(instant, instantNow);
        System.out.println(d1.getSeconds());
        System.out.println(d2.getSeconds());

        Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes);

        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dt1, newYorkOffset);
        System.out.println(dateTimeInNewYork);

        JapaneseDate japaneseDate = JapaneseDate.from(date);
        System.out.println(japaneseDate);

        Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate japanDate = japaneseChronology.dateNow();
        System.out.println(japanDate);
    }

    private static void useTemporalAdjuster() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(lastDayOfMonth());
        System.out.println(date);

        // using a custom TemporalAdjuster
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);

        // custom TemporalAdjuster
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int daysToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) daysToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) daysToAdd = 2;
            return temporal.plus(daysToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date);

        date = date.with(previousOrSame(DayOfWeek.THURSDAY));
        System.out.println(date);

        date = date.with(previous(DayOfWeek.MONDAY));
        System.out.println(date);
    }

    // custom TemporalAdjuster
    private static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    private static void useDateFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter romanianFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ro"));
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(date.format(formatter));
        System.out.println(date.format(romanianFormatter));
        System.out.println(date.format(italianFormatter));

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        System.out.println(date.format(complexFormatter));
    }

    /** Get current Hijrah date; then change it to have the first day of Ramadan, which is the first day of the 9th month. */
    private static void printRamadan() {
        HijrahDate nextRamadan = HijrahDate.now()
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(
                "Ramadan starts on " +
                IsoChronology.INSTANCE.date(nextRamadan) +
                " and ends on " +
                IsoChronology.INSTANCE.date(nextRamadan.with(TemporalAdjusters.lastDayOfMonth()))
        );
    }
}