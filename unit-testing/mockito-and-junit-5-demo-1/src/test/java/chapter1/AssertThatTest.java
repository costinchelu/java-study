package chapter1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class AssertThatTest {

    @Test
    void verify_Matcher() {
        int age = 30;
        assertAll(
                () -> assertThat(age).isEqualTo(30),
                () -> assertThat(age).isNotEqualTo(33)
        );
    }

    @Test
    void verify_multiple_values() {
        double marks = 100.00;
        assertAll(
                () -> assertThat(marks).isIn(100.00, 90.9),
                () -> assertThat(marks).isNotIn(99.99, 100.01)
        );
    }

    @Test
    void verify_collection_values() {
        List<Double> salary = Arrays.asList(50.0, 200.0, 500.0);
        assertAll(
                () -> assertThat(salary).contains(50.00, 500.0),
                () -> assertThat(salary).doesNotContain(501.00)
        );
    }

    @Test
    void verify_Strings() {
        String name = "John Jr Dale";
        assertAll(
                () -> assertThat(name).startsWith("John"),
                () -> assertThat(name).contains("Jr")
        );
    }
}
