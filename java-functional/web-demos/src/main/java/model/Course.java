package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Course {

    private final String name;

    private final CourseCategory category;

    private final int reviewScore;

    private final int noOfStudents;
}
