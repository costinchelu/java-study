package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CourseCategory {

    FRAMEWORK("Framework"),
    FULLSTACK("FullStack"),
    CLOUD("Cloud"),
    MICROSERVICES("Microservices"),
    FRONTEND("Frontend");

    private final String courseName;
}
