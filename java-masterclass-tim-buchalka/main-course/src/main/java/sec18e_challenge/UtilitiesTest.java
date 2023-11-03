package sec18e_challenge;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class UtilitiesTest {

    private Utilities util;

    @Before
    public void setup() {
        util = new Utilities();
    }

    @Test
    public void everyNthChar() {
        char[] output = util.everyNthChar(new char[] { 'h', 'e', 'l', 'l', 'o' }, 2);
        assertArrayEquals(new char[] { 'e', 'l' }, output);
        char[] output2 = util.everyNthChar(new char[] { 'h', 'e', 'l', 'l', 'o' }, 10);
        assertArrayEquals(new char[] { 'h', 'e', 'l', 'l', 'o' }, output2);
    }

    @Test
    public void removePairs() {
        //assertEquals("ABCDEF", util.removePairs("AABCDDEFF"));
        assertEquals("A", util.removePairs("A"));
        assertNull("We have a null input", util.removePairs(null));
    }

    @Test
    public void converter() {
        assertEquals(300, util.converter(10, 5));
    }

    @Test
    public void nullIfOddLength() {
        assertNull(util.nullIfOddLength("odd"));
        assertNotNull(util.nullIfOddLength("even"));
    }

    @Test(expected = ArithmeticException.class)
    public void nullIfOddLength_exception() {
        // util.converter(10, 1); -> test will fail
        util.converter(10, 0);
    }
}

