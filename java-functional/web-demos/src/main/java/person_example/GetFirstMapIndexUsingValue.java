package person_example;

import model.StudentRepo;
import model.Student;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GetFirstMapIndexUsingValue {

    public static void main(String[] args) {

        Map<String, Student> normalMap =
                StudentRepo.getAllPersons().stream()
                        .collect(Collectors.toMap(
                                (student) -> String.valueOf(student.getId()),
                                student -> student));

        normalMap.entrySet().forEach(System.out::println);

        System.out.println("-------------------------");

        List<Student> studentList = StudentRepo.getAllPersons();
        Map<Integer, String> indexNameMap = studentList.stream().collect(Collectors.toMap(studentList::indexOf, Student::getName));
        indexNameMap.forEach((row, name) -> {
            System.out.println("POSITION: " + row + " = " + name);
        });

        System.out.println(indexNameMap.get(3));
        System.out.println(getFirstKeyByValue(indexNameMap, "Dwight Eisenhower"));


    }

    public static <T, E> T getFirstKeyByValue(Map<T, E> map, E value) {
        return (T) map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }
}
