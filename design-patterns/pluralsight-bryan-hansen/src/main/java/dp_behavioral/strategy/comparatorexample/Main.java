package dp_behavioral.strategy.comparatorexample;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static void printContents(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getName() + " (" + person.getAge() + ")");
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

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                }
                if (o1.getAge() < o2.getAge()) {
                    return -1;
                }
                return 0;
            }
        });

        System.out.println("Sorted by age");
        printContents(people);


        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println("Sorted by name");
        printContents(people);

    }
}
/*
- eliminate conditional statements
- behaviour incapsulated in clases
- difficult to add new strategies
- chain_of_responsability.client chooses strategy
- can be used to externalize algorithms inside of our application

java.util.Comparator
 */