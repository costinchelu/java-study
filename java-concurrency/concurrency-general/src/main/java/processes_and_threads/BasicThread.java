package processes_and_threads;

import java.util.List;

public class BasicThread {

    public static void main(String[] args) {

        Thread t = new Thread(() -> System.out.println("another"));
        t.start();

        System.out.println("in main");


        var numbers = List.of(1, 2, 3);
        numbers.forEach(System.out::println);
        numbers.stream()
                .map(String::valueOf)
                .forEach(System.out::println);
    }
}
