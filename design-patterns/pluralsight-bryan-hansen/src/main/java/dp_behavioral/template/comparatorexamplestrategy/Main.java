package dp_behavioral.template.comparatorexamplestrategy;


import dp_behavioral.strategy.comparatorexample.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static void printContents(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getName());
        }
    }

    public static void main(String[] args) {

        Person bryan = new Person("Bryan", 39, "111-222-1111");
        Person mark = new Person("Mark", 41, "555-215-1458");
        Person chris = new Person("Chris", 38, "558-555-5258");
        Person bob = new Person("Bob", 44, "444-258-2114");
        Person john = new Person("John", 43, "447-411-1258");

        List<Person> people = new ArrayList<>();
        people.add(bryan);
        people.add(mark);
        people.add(chris);
        people.add(bob);
        people.add(john);

        System.out.println("Not sorted:");
        printContents(people);

        Collections.sort(people);

        System.out.println("\nSorted by age:");
        printContents(people);
    }
}
/*
- redefine parts of the algorithm without modifying the structure
- same algorithm but with different implementations
- class based
- template method is chosen at compile time
- code reuse
- common in libraries/frameworks
- IoC


java.utilCollections # sort()
java.util.AbstractList # indexOf()

 */