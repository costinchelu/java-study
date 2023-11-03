package chapter1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chapter1.Factorial.factorial;
import static org.assertj.core.api.Assertions.assertThat;

class FactorialTest {

    @DisplayName("Parametrized test for factorial")
    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
            "6, 720",
    })
    void factorialTest(int ordinal, int factorial) {
        assertThat(factorial(ordinal)).isEqualTo(factorial);
    }
}