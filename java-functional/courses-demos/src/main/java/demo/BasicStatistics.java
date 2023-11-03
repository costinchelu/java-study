package demo;


import model.CourseRepo;
import model.Course;
import org.junit.Test;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


/**
 * basic statistics
 * <br>
 * ofNullable
 */
public class BasicStatistics {

    private static final CourseRepo courseRepo = new CourseRepo();

    private static final List<Course> objList = courseRepo.getCourses();

    @Test
    public void whenApplySummarizingThenGetBasicStatsTest() {
        DoubleSummaryStatistics stats = objList.stream()
                .collect(Collectors
                        .summarizingDouble(Course::getNoOfStudents));
        // or
        DoubleSummaryStatistics stats2 = objList.stream()
                        .mapToDouble(Course::getNoOfStudents)
                        .summaryStatistics();

        assertEquals(stats.getCount(), 9);
        assertEquals(stats.getSum(), 181000.0, 0);
        assertEquals(stats.getMin(), 14000.0, 0);
        assertEquals(stats.getMax(), 25000.0, 0);
        assertEquals(stats.getAverage(), 20111.11, 0.01);
    }

    public static void main(String[] args) {
        Integer number = null;
        Stream<Integer> result = Stream.ofNullable(number);
        result.map(x -> x * x).forEach(System.out::println);
//        results an empty optional so that we don't get a NullPointerException
    }
}
