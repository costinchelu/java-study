package model;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PersonRepo {

    private final List<Person> people = new ArrayList<>();

    public PersonRepo() {
        people.add(new Person("Sara", 20));
        people.add(new Person("Bob", 20));
        people.add(new Person("John", 32));
        people.add(new Person("Paul", 32));
        people.add(new Person("Sara", 21));
        people.add(new Person("Jack", 3));
        people.add(new Person("Jill", 11));
        people.add(new Person("Jack", 72));
    }
}
