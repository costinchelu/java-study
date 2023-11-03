package demo;

import model.Course;
import model.CourseRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * sorted
 */
public class IntermediateOpLimitSkipTakeWhile {

    public static void main(String[] args) {

        CourseRepo coursesRepo = new CourseRepo();
        List<Course> coursesList = coursesRepo.getCourses();

        Comparator<Course> comparingByNoOfStudentsAndReviewScore =
                Comparator
                        .comparingInt(Course::getNoOfStudents)
                        .thenComparing(Course::getReviewScore)
                        .reversed();

        // limiting to top 5 results
        List<Course> limit5SortedCourses =
                coursesList.stream()
                        .sorted(comparingByNoOfStudentsAndReviewScore)
                        .limit(5)
                        .collect(Collectors.toList());

        System.out.println(limit5SortedCourses);

        // skipping top 3 results
        List<Course> skipping3SortedCourses =
                coursesList.stream()
                        .sorted(comparingByNoOfStudentsAndReviewScore)
                        .skip(3)
                        .collect(Collectors.toList());

        System.out.println(skipping3SortedCourses);

        // these 2 are intermediary operations so they can be combined
        List<Course> skipAndLimit =
                coursesList.stream()
                        .sorted(comparingByNoOfStudentsAndReviewScore)
                        .skip(3)
                        .limit(3)
                        .collect(Collectors.toList());

        System.out.println(skipAndLimit);

        // return all elements of the list until one of the list elements has a review score of 91
        // so if one of the elements breaks the criteria in the takeWhile, we will skipp all elements
        //from the stream after that particular element
        List<Course> firstCoursesUntilReviewOf92 =
                coursesList.stream()
                        .sorted(comparingByNoOfStudentsAndReviewScore)
                        .takeWhile(course -> course.getReviewScore() >= 93)
                        .collect(Collectors.toList());
        System.out.println(firstCoursesUntilReviewOf92);

        // the opposite of takeWhile is dropWhile (this will include the element that is found having
        // the required criteria:
        List<Course> coursesAfterReviewOf92 =
                coursesList.stream()
                        .sorted(comparingByNoOfStudentsAndReviewScore)
                        .dropWhile(course -> course.getReviewScore() >= 93)
                        .collect(Collectors.toList());
        System.out.println(coursesAfterReviewOf92);
    }
}
