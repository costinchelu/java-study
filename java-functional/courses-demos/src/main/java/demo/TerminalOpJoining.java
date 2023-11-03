package demo;

import model.Course;
import model.CourseRepo;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TerminalOpJoining {

    public static void main(String[] args) {

        List<String> titles = List.of("Spring", "Spring Boot", "Stream API", "Microservices", "Docker", "Linux", "Python", "React", "Java 8");
        CourseRepo coursesRepo = new CourseRepo();
        List<Course> courses = coursesRepo.getCourses();

        // collect to a specific implementation of collection
        Vector<String> courseNames = courses
                .stream()
                .map(Course::getName)
                .collect(Collectors.toCollection(Vector::new));

        // join all course names, separated by , (comma)
        String collect = titles
                .stream()
                .collect(Collectors.joining(", "));
        // equivalent with:
        String collect2 = String.join(", ", titles);

        System.out.println(collect);

        // for a list of arrays of every word in the stream:
        List<String[]> listOfArray = titles
                .stream()
                .map(course -> course.split(""))
                .collect(toList());
    }
}
