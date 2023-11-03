package model;

import lombok.Getter;

import java.util.List;


@Getter
public class CourseRepo {

    private final List<Course> courses;

    public CourseRepo() {
        courses = List.of(
                new Course("Spring", CourseCategory.MICROSERVICES, 98, 20000),
                new Course("Spring Boot", CourseCategory.FRAMEWORK, 95, 18000),
                new Course("API", CourseCategory.MICROSERVICES, 97, 22000),
                new Course("Basic Microservices", CourseCategory.MICROSERVICES, 96, 25000),
                new Course("FullStack w. Angular", CourseCategory.FULLSTACK, 91, 14000),
                new Course("AWS", CourseCategory.CLOUD, 92, 21000),
                new Course("Azure", CourseCategory.CLOUD, 99, 21000),
                new Course("Docker", CourseCategory.CLOUD, 92, 20000),
                new Course("React", CourseCategory.FRONTEND, 89, 15500),
                new Course("Kubernetes", CourseCategory.CLOUD, 91, 20000)
        );
    }
}
