package sec18e_challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilitiesParametrizedTest {

    private Utilities util;

    private String input;
    private String output;

    public UtilitiesParametrizedTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setUp() throws Exception {
        util = new Utilities();
    }

    @Parameters
    public static Collection<Object[]> testCondition() {
        return Arrays.asList(new Object[][] {
                {"ABCDEFF", "ABCDEF"},
                {"AB88EFFG", "AB8EFG"},
                {"112233445566", "123456"},
                {"A", "A"}
        });
    }

    @Test
    public void removePairs() throws Exception {
        assertEquals(output, util.removePairs(input));
    }
}
