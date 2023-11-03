package chapter3;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DurationMethods {

    public static void main(String[] args) {
        // ------------------ Factory methods for duration:
        // ISO -> PT (period of time) 4H (4 hours)  2M (2 minutes) 3S (3 seconds)
        // for nanoseconds we use second fractions.
        // there is no greater period of time than hour
        Duration PT2M3S = Duration.ofSeconds(123);
        Duration PT0000004560S = Duration.ofNanos(4560);
        Duration PT16H40M = Duration.ofMinutes(1000);
        Duration PT25H = Duration.ofHours(25);

        // using TemporalUnit
        Duration PT2H = Duration.of(2, ChronoUnit.HOURS);

        // ------------------ Adjustment methods (add seconds or nanos)
        Duration PT2M5S = PT2M3S.withSeconds(125);  // add 2 seconds to already 123 seconds
        Duration PT2M5001S = PT2M5S.withNanos(1_000_000); // add 1/100 seconds

        Duration PT2M4S = PT2M5S.minusMillis(1000);
        Duration PT4M8S = PT2M5S.plus(PT2M3S);
        Duration PTminus16H35M52S = PT4M8S.minus(PT16H40M);

        // ------------------ Accessor methods:
        Duration d = Duration.ofSeconds(62, 1_000_012);
        long getS = d.getSeconds();
        int getN = d.getNano();
        long getAllN = d.toNanos();

        // for Java 9
        long unitPart1 = d.toDaysPart();
        long unitPart2 = d.toMillisPart();
        long unitPart3 = d.toMinutesPart();
        long unitPart4 = d.toNanosPart();

        // demo
        List<Duration> durationList = Arrays.asList(
                Duration.ofSeconds(100), Duration.ofSeconds(200), Duration.ofSeconds(300)
        );
        Duration runningTotal = Duration.ZERO;
        Duration largestSoFar;

        // iterative
//        for (Duration d : durationList) {
//            runningTotal = runningTotal.plus(d);
//            largestSoFar = largestSoFar.compareTo(d) > 0 ? largestSoFar : d;
//        }

        // Stream
        // Sum the elements of durationList, and also find the largest
        runningTotal = durationList.stream().reduce(runningTotal, Duration::plus);
        largestSoFar = durationList.stream().max(Comparator.naturalOrder()).orElse(Duration.ZERO);
        System.out.printf("total = %s; largest = %s", runningTotal, largestSoFar);

    }
}
