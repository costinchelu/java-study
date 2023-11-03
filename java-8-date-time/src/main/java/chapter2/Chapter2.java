package chapter2;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class Chapter2 {

	public static void main(String[] args) {
		Chapter2Demo.runDemo();
	}
}

class Chapter2Demo {

	public static void runDemo() {
		Clock testClock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);

		// create a calendar
		SchedulerCalendar calendar = new SchedulerCalendar();

		// add some tasks to the calendar
		calendar.addTask("Answer urgent e-mail", 1, 0);
		calendar.addTask("Write deployment report", 4, 0);
		calendar.addTask("Plan security configuration", 4, 0);

		// add some work periods to the calendar
		LocalDate clockDate = LocalDate.now(testClock);
		LocalDate mondayStart = clockDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
		List<WorkPeriod> periods = WorkPeriods.generateWorkPeriods(mondayStart,3);
		calendar.addWorkPeriods(periods);

		// add an event to the calendar
		ZonedDateTime meetingStartTime = ZonedDateTime.of(
				mondayStart, LocalTime.of(7, 30), ZoneId.of("America/New_York"));
		Event nyPlanningMeeting = Event.of(meetingStartTime, Duration.ofHours(1), "New York planning");
		calendar.addEvent(nyPlanningMeeting);

		// create a working schedule
		Schedule schedule = calendar.createSchedule(mondayStart, ZoneId.of("Europe/London"));

		// and print it out
		System.out.println(schedule);
	}
}