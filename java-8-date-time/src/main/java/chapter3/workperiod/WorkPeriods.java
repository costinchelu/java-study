package chapter3.workperiod;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;

/** utility methods for WorkPeriod
 */
public class WorkPeriods {

    public static final LocalTime AM_START_TIME = LocalTime.of(9, 0);
    public static final LocalTime PM_START_TIME = LocalTime.of(13, 30);
    public static final Duration WORK_PERIOD_LENGTH = Duration.ofHours(3).plusMinutes(30);

    public static WorkPeriod createFirstWorkPeriod() {
        LocalDateTime startDateTime;

        // alternative I:
        startDateTime = LocalDateTime.of(1970, 1, 5, 9, 0, 0);

        // alternative II:
        LocalDate startDate = LocalDate.of(1970, 1, 5);
        LocalTime startTime = LocalTime.of(9, 5);
        startDateTime = LocalDateTime.of(startDate, startTime);

        // alternative ways to create a duration
        Duration morningLength;
        morningLength = Duration.ofHours(3).plusMinutes(30);
        // or
        morningLength = Duration.ofMinutes(210);

        LocalDateTime endDateTime;
        // alternative ways to create a LocalDateTime
        endDateTime = startDateTime.with(LocalTime.of(12, 30));
        // or:
        endDateTime = startDateTime.plus(morningLength);

        // call WorkPeriod factory method
        WorkPeriod wp = WorkPeriod.of(startDateTime, endDateTime);
        return wp;
    }

    public static WorkPeriod createMorningWorkPeriod(LocalDate date) {
        LocalDateTime startDateTime = LocalDateTime.of(date, AM_START_TIME);
        LocalDateTime endDateTime = startDateTime.plus(WORK_PERIOD_LENGTH);
        return WorkPeriod.of(startDateTime, endDateTime);
    }
    public static WorkPeriod createAfternoonWorkPeriod(LocalDate date) {
        LocalDateTime startDateTime = LocalDateTime.of(date, PM_START_TIME);
        LocalDateTime endDateTime = startDateTime.plus(WORK_PERIOD_LENGTH);
        return WorkPeriod.of(startDateTime, endDateTime);
    }

    public static List<WorkPeriod> generateWorkPeriods(LocalDate startDate, int dayCount) {
        List<LocalDate> workingDays = generateWorkingDays(startDate, dayCount);
        return generateWorkPeriods(workingDays);
    }

    // generate morning and afternoon work periods for each day in workingDays
    private static List<WorkPeriod> generateWorkPeriods(List<LocalDate> workingDays) {
        return workingDays.stream()
                .flatMap(d -> Stream.of(createMorningWorkPeriod(d),createAfternoonWorkPeriod(d)))
                .collect(toList());
    }

    private static List<WorkPeriod> generateWorkPeriods_loopVersion(List<LocalDate> workingDays) {
        List<WorkPeriod> workPeriods = new ArrayList<>();
        for (LocalDate d : workingDays) {
            workPeriods.add(createMorningWorkPeriod(d));
            workPeriods.add(createAfternoonWorkPeriod(d));
        }
        return workPeriods;
    }

    private static List<LocalDate> generateWorkingDays(LocalDate startDate, int dayCount) {
        return Stream.iterate(startDate, d -> d.with(nextWorkingDayAdjuster))
                .limit(dayCount)
                .collect(toList());
    }

    private static List<LocalDate> generateWorkingDays_loopVersion(LocalDate startDate, int dayCount) {
        int i = 0;
        List<LocalDate> workingDays = new ArrayList<>();
        for (LocalDate date = startDate; i < dayCount; date = date.plusDays(1)) {
            if (isWorkingDay(date)) {
                workingDays.add(date);
                i = i + 1;
            }
        }
        return workingDays;
    }

    private final static TemporalAdjuster nextWorkingDayAdjuster =
            d -> DayOfWeek.from(d) != FRIDAY
                    ? d.plus(1, DAYS)
                    : d.with(TemporalAdjusters.next(MONDAY));

    private static boolean isWorkingDay(LocalDate d) {
        DayOfWeek dayOfWeek = d.getDayOfWeek();
        return ! (dayOfWeek == SATURDAY || dayOfWeek == SUNDAY);
    }

    public static void main(String[] args) {
        List<WorkPeriod> workPeriods = generateWorkPeriods(LocalDate.ofEpochDay(0), 3);
        workPeriods.forEach(System.out::println);
    }
}