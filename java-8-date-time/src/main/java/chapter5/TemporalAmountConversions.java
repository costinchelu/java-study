package chapter5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class TemporalAmountConversions {

    public static void main(String[] args) {

        Period period1 = Period.between(LocalDate.EPOCH, LocalDate.now());

        System.out.printf("%d years, %d months, %d days\n",
                period1.getYears(), period1.getMonths(), period1.getDays());

        Duration duration1 = Duration.ofSeconds(34567);

        System.out.println(duration1.toMinutes() + " total minutes");

        System.out.printf("%d hours, %d minutes, %d seconds, %d milliseconds\n",
                duration1.toHoursPart(), duration1.toMinutesPart(), duration1.toSecondsPart(), duration1.toMillisPart());
        // obs to...Part() are available from Java 9

        // for a duration < 24 hours we can:
        String duration1Formatted = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.MIDNIGHT.plus(duration1));
    }
}
