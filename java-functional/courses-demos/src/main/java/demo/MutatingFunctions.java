package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * MUTATING FUNCTIONS
 * <br>
 * replace all
 * <br>
 * remove if
 */
public class MutatingFunctions {

    private static final List<String> titles =
            List.of("Spring", "Spring Boot", "Stream API", "Microservices", "Docker", "Linux", "Python", "React", "Java 8");


    public static void main(String[] args) {

        List<String> modifiableCourses = new ArrayList<>(titles);
        System.out.println(modifiableCourses);

        // REPLACE ALL
        modifiableCourses.replaceAll(String::toUpperCase);
        System.out.println(modifiableCourses);

        // REMOVE IF
        modifiableCourses.removeIf(course -> course.length() < 6);
        System.out.println(modifiableCourses);
    }
}
