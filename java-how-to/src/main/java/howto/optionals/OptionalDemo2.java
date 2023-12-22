package howto.optionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OptionalDemo2 {

    public static void main(String[] args) {
        OptionalDemo2 demo = new OptionalDemo2();

        Optional<Object> empty = Optional.empty();
        Optional<Integer> intOptional = Optional.of(95);
        Optional<Double> average1 = average();
        Optional<Double> average2 = average(10, 10, 9);

//        if (average1.isPresent())
//            System.out.println(average1);

        average1.ifPresent(System.out::println);
        average2.ifPresent(System.out::println);
        System.out.println(average1.orElse(Double.NaN));

        double value = 0;
        Optional<Double> value1 = Optional.ofNullable(value);
        value1.ifPresent(System.out::println);
    }

    public static Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score : scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::name)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::name))
                .flatMap(Optional::stream)
                .collect(toSet());
    }

    // optional chaining
    private static void threeDigit(Optional<Integer> optional) {
        optional.map(n -> "" + n)
                .filter(s -> s.length() == 3)
                .ifPresent(System.out::println);
    }
}

record Insurance(String name) {

}

@AllArgsConstructor
@NoArgsConstructor
class Car {

    private Insurance insurance;

    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance);
    }
}

@AllArgsConstructor
@NoArgsConstructor
class Person {

    private Car car;

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }
}