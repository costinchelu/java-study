package demo;

import model.Course;
import model.CourseRepo;

import java.util.List;

/**
 * average, sum, count, min, max
 */
public class IntermediateOpMapToIntMax {

    public static void main(String[] args) {

        CourseRepo coursesRepo = new CourseRepo();
        List<Course> coursesList = coursesRepo.getCourses();

        // get total number of students in the courses with review > 95
        System.out.println(
                coursesList.stream()
                        .filter(c -> c.getReviewScore() > 95)
                        .mapToInt(Course::getNoOfStudents)
                        .sum());

        // we also have for average (that returns an Optional<Double>)
        coursesList.stream()
                .filter(c -> c.getReviewScore() > 95)
                .mapToInt(Course::getNoOfStudents)
                .average()
                .ifPresent(System.out::println);

        // count (4 courses meeting the criteria)
        System.out.println(
                coursesList.stream()
                        .filter(c -> c.getReviewScore() > 95)
                        .mapToInt(Course::getNoOfStudents)
                        .count());

        // maximum number of students in one of the courses
        // that match the criteria (returns an Optional<Integer>)
        coursesList.stream()
                .filter(c -> c.getReviewScore() > 95)
                .mapToInt(Course::getNoOfStudents)
                .max()
                .ifPresent(System.out::println);
    }
}
