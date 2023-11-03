package chapter1;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

class AssumptionTest {

    boolean isSonarRunning = false;

    // assumption that isSonarRunning will decide if the test will run or not
    @Test
    void very_critical_test() throws Exception {
        isSonarRunning = true;
        Assumptions.assumeFalse(isSonarRunning);
        assertTrue(true);
    }

    @Test
    void very_critical_test2() throws Exception {
        isSonarRunning = false;
        Assumptions.assumeFalse(isSonarRunning);
        assertTrue(true);
    }
}
