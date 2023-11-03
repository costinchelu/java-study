package chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertTest {

    @Test
    void assertTrueAndFalseTest() {
        assertAll(
                () -> assertTrue(true),
                () -> assertFalse(false)
        );
    }

    @Test
    void assertNullAndNotNullTest() {
        Object myObject = null;
        assertNull(myObject);
        myObject = "Some value";
        assertNotNull(myObject);
    }

    @Test
    void assertEqualsTest() {
        Integer i = Integer.valueOf("5");
        Integer j = Integer.valueOf("5");
        ;
        assertEquals(i, j);
    }

    @Test
    void assertSameTest() {
        Integer i = Integer.parseInt("5");
        Integer j = Integer.parseInt("5");
        Integer k = j;
        assertAll(
                () -> assertNotSame(i, j),
                () -> assertSame(j, k)
        );
    }

    @Test
    void exceptionTest() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    throw new RuntimeException();
                });
    }
}
