package chapter4;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class UsingTemporalAmount {

    public static void main(String[] args) {
        LocalDate periodStart = LocalDate.of(2015, 3, 27);
        LocalDate periodEnd = LocalDate.of(2016, 3, 2);

        // period between includes the start date and excludes the end date
        Period oneDay = Period.between(periodStart, periodStart.plusDays(1));   // P1D

        // so:
        Period periodBetween = Period.between(periodStart, periodEnd);  // P11M4D
        long monthsBetween = ChronoUnit.MONTHS.between(periodStart, periodEnd);

        // using time also:
        ZonedDateTime startTime = ZonedDateTime.of(periodStart, LocalTime.of(19, 42), ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(periodEnd, LocalTime.of(4, 25), ZoneOffset.UTC);

        Duration durationBetween = Duration.between(startTime, endTime); // PT8168H43M
        long hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);

        // it can automatically extract date from ZonedDateTime
        long monthsBetween2 = ChronoUnit.MONTHS.between(periodStart, endTime);

        // can also use

        // between method results can differ in case we use a time based temporal (like ZonedDateTime) comparing to
        // a date base temporal (LocalDate) when we ask for ChronoUnit.DAYS
        long daysBetweenUsingDateBasedTemporal = ChronoUnit.DAYS.between(periodStart, periodEnd);
        //vs
        long daysBetweenUsingTimeBasedTemporal = ChronoUnit.DAYS.between(startTime, endTime);

        // we can also use until (same as between, different style)
        long daysUntil = startTime.until(endTime, ChronoUnit.DAYS);
    }
}
