package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


class JUnit5FauxTest {

    @Test
    void someTestForJUnit5() {
        System.out.println("I Ran....");
        assertNotEquals(5, 2);
    }
}
