package demo;

import model.CourseRepo;

/**
 * Lazy operations
 <p></p>
 As soon as it finds the first element (findFirst) that complies with the filter, then it does not need
 to go through the rest of the stream (lazy operations for intermediate results)
 <p>
 In this example the result is that the first peek will not print elements after "Microservices" Course and
 second peek will print only the element found in uppercase (not all elements).
 Find first returns an optional
 */
public class LazyOperations {

    private static final CourseRepo coursesRepo = new CourseRepo();

    public static void main(String[] args) {

        var result = coursesRepo.getCourses().stream()
                .peek(System.out::println)
                .filter(course -> course.getName().length() > 11)
                .map(course -> course.getName().toUpperCase())
                .peek(System.out::println)
                .findFirst();

        System.out.println("-----------------------------result:");
        System.out.println(result.orElse("NO RESULT"));
    }
}