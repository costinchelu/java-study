package demo;

import model.Course;
import model.CourseRepo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Collecting to map
 */
public class GroupingCourseExamples {

    public static void main(String[] args) {
        CourseRepo coursesRepo = new CourseRepo();
        List<Course> courses = coursesRepo.getCourses();

        // collecting to a map (1:1)
        Map<String, Course> simpleMapCollection =
                courses.stream()
                        .collect(Collectors.toMap(
                                course -> course.getName() + " [" + course.getCategory() + "]",
                                course -> course
                        ));

        // getting a map of courses classified (grouped) by course category
        Map<String, List<Course>> mapByCategory =
                courses.stream()
                        .collect(Collectors.groupingBy(c -> c.getCategory().getCourseName()));

        System.out.println(mapByCategory);

        // getting a map of categories with a count value (pass a function to create a value)
        Map<String, Long> noOfCoursesByCategory =
                courses.stream()
                        .collect(Collectors.groupingBy(
                                c -> c.getCategory().getCourseName(),
                                Collectors.counting()));

        // getting a map consisting of the highest review for a particular course category
        Map<String, Optional<Course>> highestReviewInCategory =
                courses.stream()
                        .collect(Collectors.groupingBy(
                                c -> c.getCategory().getCourseName(),
                                Collectors.maxBy(
                                        Comparator.comparingInt(Course::getReviewScore))));

        // getting a map consisting of names of the courses by category
        Map<String, List<String>> highestReviewNames =
                courses.stream()
                        .collect(Collectors.groupingBy(
                                c -> c.getCategory().getCourseName(),
                                Collectors.mapping(
                                        Course::getName,
                                        Collectors.toList())));

        System.out.println(highestReviewNames);

        // partition (grouping by boolean values)
        List<Integer> intList = List.of(2, 4, 5, 6, 8, 7, 11, 14, 13, 8);
        Map<Boolean, List<Integer>> isEvenOrOdd = intList.stream().collect(
                Collectors.partitioningBy(i -> i % 2 == 0));

        System.out.println("-------");
    }
}
