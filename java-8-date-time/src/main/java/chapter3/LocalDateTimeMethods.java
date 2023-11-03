package chapter3;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeMethods {

    public static void main(String[] args) {

        // Temporal interface
        System.out.println(LocalDateTime.now());

        // passing a Clock

        Clock utcClock = Clock.systemUTC();
        // will return the dateTime using UTC time zone
        System.out.println(LocalDateTime.now(utcClock));

        Clock localClock = Clock.systemDefaultZone();
        Clock localClock0Nanos = Clock.tickSeconds(ZoneId.systemDefault());
        Clock anotherZone = Clock.system(ZoneId.of("America/New_York"));

        // will return the dateTime using local time zone
        System.out.println(LocalDateTime.now(localClock));

        Clock fixedClock = Clock.fixed(Instant.ofEpochSecond(946_684_800), ZoneId.of("UTC"));
        Clock fixedClock2 = Clock.fixed(Instant.now(), ZoneOffset.UTC);

        // will return a fixed dateTime for 2000-01-01T00:00
        System.out.println(LocalDateTime.now(fixedClock));

        // returns 00:05
        System.out.println(LocalTime.MIDNIGHT.plusMinutes(5));
        System.out.println(LocalTime.MIDNIGHT.plus(Duration.ofMinutes(5)));
        System.out.println(LocalTime.MIDNIGHT.plus(5, ChronoUnit.MINUTES));

        // returns 12:00
        System.out.println(LocalTime.MIDNIGHT.with(ChronoField.HOUR_OF_DAY, 12));

        // using a fixed local time from 2017 october 01 00:00 UTC
        Clock localTimeOn20171001midnight = Clock.fixed(Instant.ofEpochSecond(1_506_816_000), ZoneId.of("UTC"));
        LocalDateTime work = LocalDateTime.now(localTimeOn20171001midnight);

        // Temporal accessors
        int getYear = work.getYear();
        int getMonth = work.get(ChronoField.MONTH_OF_YEAR);
        String dayOfWeek = work.getDayOfWeek().toString();
        LocalDate localDate = LocalDate.from(work);
        LocalTime localTime = LocalTime.from(work);

        /*
        Make date-time and time zone dependencies explicit:
            always use the .now(Clock) and Clock.getZone() methods
         */
    }
}
