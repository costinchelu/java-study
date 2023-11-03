package venkats;


import model.Person;
import model.PersonRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExploringCollectors {

    public static void main(String[] args) {
        PersonRepo repo = new PersonRepo();
        List<Person> people = repo.getPeople();

        // functional
        Integer totalAge = people.stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum);

        // collectors are reduce operations
        List<String> peopleOlderThan30 = people.stream()
                .filter(p -> p.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toUnmodifiableList());

        // create a string with comma separated values

        // imperative
        StringBuilder namesOfPeopleGt30AgeImperative = new StringBuilder();
        for (Person p : people) {
            if (p.getAge() > 30) {
                namesOfPeopleGt30AgeImperative.append(p.getName().toUpperCase()).append(",");
            }
        }

        // functional
        String namesOfPeopleGt30AgeFunctional = people.stream()
                .filter(p -> p.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));

        // split the list by age even or odd
        Map<Boolean, List<Person>> partByAgeOddOrEven = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() % 2 == 0));


        // group the people based on their name

        // imperative
        Map<String, List<Person>> byNameImperative = new HashMap<>();
        List<Person> tempList;
        for (Person p : people) {
            if (byNameImperative.containsKey(p.getName())) {
                tempList = byNameImperative.get(p.getName());
            } else {
                tempList = new ArrayList<>();
                byNameImperative.put(p.getName(), tempList);
            }
            tempList.add(p);
        }

        // functional
        Map<String, List<Person>> byNameFunctional = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getName));

        // group by name (having a list of ages
        Map<String, List<Integer>> byNameListOfAges = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.mapping(
                                Person::getAge,
                                Collectors.toList())
                ));

        Map<String, Long> countByName = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.counting()
                ));

        Map<String, Integer> countByNameToInt = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                Long::intValue
                        )
                ));

        Map<Integer, List<String>> byAgeAndOnlySome = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(
                                Person::getName,
                                Collectors.filtering(
                                        name -> name.startsWith("J") || name.endsWith("l"),
                                        Collectors.toList()))
                ));

        // maxBy returns an Optional, and we can run map on it
        String getSomething = people.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.maxBy(
                                Comparator.comparing(Person::getAge)),
                                optionalPerson -> optionalPerson.map(
                                        op -> op.getName() + "|" + op.getAge()).orElse("")
                ));


        // for a one-to-one function, use map to go from Stream<T> -> Stream<R>
        // for a one-to-many function, use map to go from Stream<T> -> Stream<Collection<R>>
        // for a one-to-many function, use flatMap to go from Stream<T> -> Stream<R>

        List<List<? extends Number>> listOfLists = Stream.of(1, 2, 3)
                .map(element -> List.of(element - 0.5, element, element + 0.5))
                .collect(Collectors.toList());

        List<? extends Number> flattenedList = Stream.of(1, 2, 3)
                .flatMap(element -> Stream.of(element - 0.5, element, element + 0.5))
                .toList();

        Map<Integer, List<String>> listOfNameCharacters = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.flatMapping(
                                p -> Stream.of(p.getName().split("")),
                                Collectors.toList())
                ));
    }
}
