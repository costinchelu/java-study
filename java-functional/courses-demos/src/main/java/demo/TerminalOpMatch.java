package demo;

import model.Course;
import model.CourseRepo;

import java.util.List;

/**
 * all match
 * <br>
 * any match
 * <br>
 * none match
 */
public class TerminalOpMatch {

    public static void main(String[] args) {
        CourseRepo coursesRepo = new CourseRepo();
        List<Course> coursesList = coursesRepo.getCourses();

        System.out.println("Are ALL courses HAVING a review score of over 90?\n" +
                coursesList.stream().allMatch(course -> course.getReviewScore() > 90));

        System.out.println("Are ALL courses NOT HAVING a review score of over 95?\n" +
                coursesList.stream().noneMatch(course -> course.getReviewScore() > 95));

        System.out.println("Are AT LEAST ONE course HAVE a review score of over 95?\n" +
                coursesList.stream().anyMatch(course -> course.getReviewScore() > 95));
    }
}
