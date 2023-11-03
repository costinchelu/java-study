package chapter2;

import chapter2.WorkPeriod;
import chapter2.WorkPeriods;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class WorkPeriodsTest {

	@Test
	public void testGenerateWorkPeriods() {
		LocalDate thur24May2017 = LocalDate.of(2018, 5, 24);
		List<WorkPeriod> workPeriods = WorkPeriods.generateWorkPeriods(thur24May2017, 3);
		assertEquals(6, workPeriods.size());
		assertEquals(Arrays.asList(THURSDAY, FRIDAY, MONDAY),
				workPeriods.stream()
						.map(WorkPeriod::getStartTime)
						.map(LocalDateTime::getDayOfWeek)
						.distinct()
						.collect(toList()));
	}
}
