package demo;

import model.Course;
import model.CourseRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * comparator
 */
public class IntermediateOpSorted {

    public static void main(String[] args) {
        CourseRepo coursesRepo = new CourseRepo();
        List<Course> coursesList = coursesRepo.getCourses();

        Comparator<Course> comparingByNoOfStudents = Comparator.comparing(Course::getNoOfStudents);
        Comparator<Course> comparingByNoOfStudentsDesc = Comparator.comparing(Course::getNoOfStudents).reversed();
        Comparator<Course> comparingByReviewScore = Comparator.comparing(Course::getReviewScore);

        Comparator<Course> comparingByNoOfStudentsAndReviewScore =
                Comparator
                        .comparingInt(Course::getNoOfStudents)
                        .thenComparing(Course::getReviewScore)
                        .reversed();
        // using primitive comparing function is more efficient when we are using primitives (no boxing-unboxing)


        List<Course> sortedByNoOfStudents =
                coursesList.stream()
                .sorted(comparingByNoOfStudents)
                .collect(Collectors.toList());

        List<Course> sortedByNoOfStudentsDescent =
                coursesList.stream()
                .sorted(comparingByNoOfStudentsDesc)
                .collect(Collectors.toList());

        List<Course> sortedByNoOfStudentsAndReviewScore =
                coursesList.stream()
                .sorted(comparingByNoOfStudentsAndReviewScore)
                .collect(Collectors.toList());


        System.out.println(sortedByNoOfStudentsAndReviewScore);
    }
}
