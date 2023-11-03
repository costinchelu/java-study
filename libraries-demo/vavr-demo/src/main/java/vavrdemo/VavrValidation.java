package vavrdemo;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VavrValidation {

    private PersonValidator personValidator;

    public void vavrValidationDemo() {
        log.info("--> Validation -->");


        // Valid(Person(John Doe, 30))
        Validation<Seq<String>, Person> valid = personValidator
                .validatePerson(Person.builder().name("John Doe").age(30).build());

        // Invalid(List(Name contains invalid characters: '!4?', Age must be at least 0))
        Validation<Seq<String>, Person> invalid = personValidator
                .validatePerson(Person.builder().name("John? Doe!4").age(-1).build());

        log.info("Valid person: {}", valid);
        log.info("Invalid person: {}", invalid);
    }
}

@Component
class PersonValidator {

    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";

    private static final int MIN_AGE = 0;

    private static final int MAX_AGE = 130;

    public Validation<Seq<String>, Person> validatePerson(Person person) {
        return Validation
                .combine(validateName(person.getName()), validateAge(person.getAge()))
                .ap(Person::new);
    }

    private Validation<String, String> validateName(String name) {
        return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid("Name contains invalid characters: << " + seq.distinct().sorted() + " >>"));
    }

    private Validation<String, Integer> validateAge(int age) {
        return age < MIN_AGE || age > MAX_AGE
                ? Validation.invalid("Age must be between " + MIN_AGE + " and " + MAX_AGE)
                : Validation.valid(age);
    }
}

@AllArgsConstructor
@Data
@Builder
class Person {

    public final String name;

    public final int age;
}
