package model;

import lombok.Getter;

import java.util.List;

import static model.CourseCategory.CLOUD;
import static model.CourseCategory.FRAMEWORK;
import static model.CourseCategory.FRONTEND;
import static model.CourseCategory.FULLSTACK;
import static model.CourseCategory.MICROSERVICES;


@Getter
public class CourseRepo {

    private final List<Course> courses;

    public CourseRepo() {
        courses = List.of(
                new Course("Spring", MICROSERVICES, 98, 20000),
                new Course("Spring Boot", FRAMEWORK, 95, 18000),
                new Course("API", MICROSERVICES, 97, 22000),
                new Course("Basic Microservices", MICROSERVICES, 96, 25000),
                new Course("FullStack w. Angular", FULLSTACK, 91, 14000),
                new Course("AWS", CLOUD, 92, 21000),
                new Course("Azure", CLOUD, 99, 21000),
                new Course("Docker", CLOUD, 92, 20000),
                new Course("React", FRONTEND, 89, 15500),
                new Course("Kubernetes", CLOUD, 91, 20000)
        );
    }
}
