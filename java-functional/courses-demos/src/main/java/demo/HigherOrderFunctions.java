package demo;

import model.Course;
import model.CourseCategory;
import model.CourseRepo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Higher order function (function that returns a function)
 <p></p>


 In normal functions or methods, we usually pass either primitive types or an object as an argument and
 if needed the function can return a primitive or an object.
 <p>
 Then comes first-class functions.<br>
 First-class functions are the functions that are treated as values.<br>
 It means, those functions can be assigned to a variable and can be passed around as an argument.
<p>
 And finally, comes higher-order functions.<br>
 These are the functions that either takes one or more function as an argument or returns a function as a result.
<p></p>
 The two things (First class function and higher-order function) are closely related.

 Before Java 8, we used to pass a function to a function with the help of anonymous inner classes,
 but now we do that with the help of Lambdas.


*/
public class HigherOrderFunctions {

    private static final CourseRepo repo = new CourseRepo();

    public static void main(String[] args) {

        Predicate<Course> reviewScoreGreaterThan95Predicate = createReviewScorePredicate(95);

        Predicate<Course> reviewScoreGreaterThan85Predicate = createReviewScorePredicate(85);

        /*
        Below, filter(Predicate<>) is the higher-older function - it takes a predicate as an argument
         */
        List<Course> filteredCoursesVar1 = repo.getCourses().stream()
                .filter(reviewScoreGreaterThan95Predicate)
                .toList();

        List<Course> filteredCoursesVar2 = repo.getCourses().stream()
                .filter(createReviewScorePredicate(95))
                .toList();

        filteredCoursesVar2.forEach(System.out::println);

        List<String> coursesStartingWithS = repo.getCourses()
                .stream()
                .map(Course::getName)
                .filter(checkIfStartsWith("S"))
                .toList();
        coursesStartingWithS.forEach(System.out::println);


        // for the next example, the "action" starts in the process method when we call the accept method of the consumer
        consumeBasedOnCourseCategory("Cloud");
        consumeBasedOnCourseCategory("Microservices");

        var courses = repo.getCourses();
        List<Object> allCoursesInSameCatAsAPI = courses
                .stream()
                .filter(c -> c.getName().equals("API"))
                .map(Course::getCategory)
                .map(getTheCategoryFunction(courses))
                .collect(Collectors.toList());
        System.out.println(" ");
    }

    /**
     * this method is actually just defining the consumer (as a lambda)<br>
     * it will call the process method that will get a map and that map will be consumed/processed inside the lambda
     *
     * @param categoryName = the name of the category to process
     */
    private static void consumeBasedOnCourseCategory(String categoryName) {
        HigherOrderFunctions hof = new HigherOrderFunctions();
        hof.process(
                categoryName,
                generatedMap -> {
                    for (Map.Entry<Integer, List<Course>> entry : generatedMap.entrySet()) {
                        System.out.println("For score: " + entry.getKey());
                        entry.getValue().forEach(System.out::println);
                    }
                }
        );
    }

    /**
     * The logic for this method is actually in <code>consumeBasedOnCourseCategory</code> method
     <p>
    <b>(this is another way of writing it)</b>
    */
    private static Consumer<Map<Integer, List<Course>>> processMapConsumer() {
        return m -> {
            for (Map.Entry<Integer, List<Course>> entry : m.entrySet()) {
                System.out.println("For score: " + entry.getKey());
                entry.getValue().forEach(System.out::println);
            }
        };
    }

    /**
     Here the process method is called by <code>consumeBasedOnCourseCategory</code>
     and the consumer is called inside this method, after the map is generated
      */

    private void process(String name, Consumer<Map<Integer, List<Course>>> consumer) {
        List<Course> courses = repo.getCourses();

        Map<Integer, List<Course>> generatedMap = courses.stream()
                .filter(c -> c.getCategory().getCourseName().equals(name))
                .peek(System.out::println)
                .collect(Collectors.groupingBy(Course::getReviewScore));

        consumer.accept(generatedMap);

        /* can also be written:
                consumer.accept(courses.stream()
                        .filter(c -> c.getCategory().getCourseName().equals(name))
                        .peek(System.out::println)
                        .collect(Collectors.groupingBy(Course::getReviewScore))
                 );
         */
    }

    private static Function<CourseCategory, List<Object>> getTheCategoryFunction(List<Course> courses) {
        System.out.println("getTheCategoryFunction is called");
        return courseCategory -> {
            List<Course> filteredCoursesList = courses.stream().filter(c -> c.getCategory().equals(courseCategory)).toList();
            filteredCoursesList.forEach(printTheCategoryConsumer());
            return wrap(filteredCoursesList);
        };
    }

    private static Consumer<Course> printTheCategoryConsumer() {
        System.out.println("printTheCategoryConsumer is called");
        return course -> System.out.println(course.getName());
    }

    private static List<Object> wrap(Object in) {
        return in == null ? Collections.emptyList() : List.of(in);
    }

    /**
     <b>Example of higher order function</b>><br>
    Instead of a value, this method returns logic
    the method is applying only to a Course class, and uses an integer as parameter in order to return the result
     */
    private static Predicate<Course> createReviewScorePredicate(int cutoffReviewScore) {
        return course -> course.getReviewScore() > cutoffReviewScore;
    }

    private static Predicate<String> checkIfStartsWith(final String letter) {
        return word -> word.startsWith(letter);
    }

    // other examples:

    private static Function<String, Predicate<String>> checkIfStartsWith() {
        return letter -> (word -> word.startsWith(letter));
    }

    private static BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> getList() {
        return (list, predicate) -> list.stream().filter(predicate).collect(Collectors.toList());
    }
}
