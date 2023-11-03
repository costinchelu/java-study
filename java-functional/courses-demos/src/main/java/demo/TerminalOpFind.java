package demo;

import model.Course;
import model.CourseRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * find first
 * <br>
 * find any
 */
public class TerminalOpFind {

    public static void main(String[] args) {

        CourseRepo coursesRepo = new CourseRepo();
        List<Course> coursesList = coursesRepo.getCourses();

        Comparator<Course> comparingByNoOfStudentsAndReviewScore =
                Comparator
                        .comparingInt(Course::getNoOfStudents)
                        .thenComparing(Course::getReviewScore)
                        .reversed();

        // max returns the last element in the list
        // min returns first element in the list
        Optional<Course> maxCourse = coursesList.stream()
                .max(comparingByNoOfStudentsAndReviewScore);

        maxCourse.ifPresent(System.out::println);

        coursesList.stream()
                .max(Comparator
                        .comparingInt(Course::getNoOfStudents)
                        .thenComparing(Course::getReviewScore))
                .ifPresent(System.out::println);

        coursesList.stream()
                .min(comparingByNoOfStudentsAndReviewScore)
                .ifPresent(System.out::println);

        // if we get an empty optional we can check and take appropriate
        // measures. We can also return a default value instead of null,
        // by using .orElse(new Course(...))

        // find any will get a nondeterministic value
        coursesList.stream()
                .filter(c -> c.getReviewScore() > 95)
                .findFirst()
                .ifPresent(System.out::println);

        coursesList.stream()
                .filter(c -> c.getReviewScore() <= 95)
                .findAny()
                .ifPresent(System.out::println);
    }
}
