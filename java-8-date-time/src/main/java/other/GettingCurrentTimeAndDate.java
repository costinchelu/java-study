package other;


import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


public class GettingCurrentTimeAndDate {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");


    public static void main(String[] args) {

        // USING CALENDAR
        Calendar calendar = Calendar.getInstance();
        System.out.println(SIMPLE_DATE_FORMAT.format(calendar.getTime()));

        // USING LOCAL DATE
        // instead of initializing a new object, we're calling the static method now()
        // which returns the current date according to the system clock, with the default time-zone.
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(DATE_FORMATTER));

//        ZoneId.getAvailableZoneIds()
//                .stream().
//                filter(z -> z.startsWith("Asia"))
//                .forEach(System.out::println);

        LocalDate localDate1 = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(localDate1.format(DATE_FORMATTER));

        // USING LOCAL TIME
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.format(TIME_FORMATTER));

        System.out.println("5th of October 2021 will be: " +
                LocalDate.of(2021, Month.OCTOBER, 5).getDayOfWeek());

        // USING LOCAL DATE TIME
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DATE_TIME_FORMATTER));

        System.out.println("Tommorow will be: " +
                LocalDateTime.now().plus(1, ChronoUnit.DAYS).getDayOfWeek());

        LocalDateTime sylvester = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // FRIDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 EET 2021

        // USING ZONED DATE TIME
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.format(ZONED_DATE_TIME_FORMATTER));

        // USING CLOCK

        // clock with the system's default timezone
        Clock clock = Clock.systemDefaultZone();

        // clock with the UTC timezone
        Clock clock1 = Clock.systemUTC();

        // clock with another timezone
        Clock clock2 = Clock.system(ZoneId.of("Europe/Paris"));

        Instant instant1 = clock2.instant();
        System.out.println(instant);

        System.out.println(clock2.millis());

        // Each of these obtain the current instance of that class from the clock
        LocalDateTime localDateTime1 = LocalDateTime.now(clock);
        LocalTime localTime1 = LocalTime.now(clock);
        LocalDate localDate2 = LocalDate.now(clock);
        Instant instant2 = Instant.now(clock);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(clock);
    }
}
