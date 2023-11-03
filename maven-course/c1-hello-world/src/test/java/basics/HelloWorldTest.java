package basics;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloWorldTest {

    @Test
    void test1() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void test2() {
        assertTrue(2 > 1);
    }
}