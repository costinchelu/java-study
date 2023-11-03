package model;


import java.util.ArrayList;
import java.util.List;

public class StudentRepo {

    public static List<Student> getAllPersons() {
        List<Student> studentList = new ArrayList<>();

        Student p1 = new Student(1L, "John Withmore", "USA", 1.72, 78);
        Student p2 = new Student(2L, "Benjamin Tell", "USA", 1.64, 74);
        Student p3 = new Student(3L, "Abraham Sikorski", "USA-N", 1.85, 83);
        Student p4 = new Student(4L, "Theodore Fisherman", "USA", 1.82, 88);
        Student p5 = new Student(5L, "Thomas Moore", "US", 1.83, 74);
        Student p6 = new Student(6L, "Alexandra Proudmoore", "USA", 1.63, 87);

        studentList.add(p3);
        studentList.add(p1);
        studentList.add(p4);
        studentList.add(p6);
        studentList.add(p5);
        studentList.add(p2);

        return studentList;
    }
}
