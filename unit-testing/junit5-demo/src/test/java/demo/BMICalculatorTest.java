package demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "dev";

    // nested
    // we can group tests within the suite using inner classes

    @Nested
    @DisplayName("Tests for BMICalculator.isDietRecommended")
    class IsDiedRecommendedTests {

        @Test
        @Disabled
        void thisTestWillNotRun() {
            fail("If this test runs, it will fail");
        }

        @Test
        void shouldReturnTrueWhenDiedRecommended() {
            assertTrue(BMICalculator.isDietRecommended(85.0, 1.80));
        }

        @Test
        @DisabledOnOs(OS.LINUX)
        void shouldReturnFalseWhenDiedNotRecommended() {
            // given
            double weight = 70;
            double height = 1.8;
            // when
            boolean isRecommended = BMICalculator.isDietRecommended(weight, height);
            // then
            assertFalse(isRecommended);
        }

        @Test
        void shouldThrowArithmeticExceptionWhenHeightZero() {
            // given
            double weight = 70;
            double height = 0;
            // when
            Executable catchExecution = () -> BMICalculator.isDietRecommended(weight, height);
            // then
            assertThrows(ArithmeticException.class, catchExecution);
        }
    }

    @Nested
    @DisplayName("Tests for BMICalculator.findCoderWithWorstBMI")
    class findCodersWithWorseBmiTests {
        @Test
        void shouldReturnCoderWithWorstBMIWhenCoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.8, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            // when
            Coder coderWorstBmi = BMICalculator.findCoderWithWorstBMI(coders);
            // then
            // assertions that have a relation, can be grouped together with assertAll
            assertAll("Should return correct values for both weight and height of the Coder obj, with a certain precision",
                    () -> assertEquals(1.82004, coderWorstBmi.getHeight(), 0.0001),
                    () -> assertEquals(98, coderWorstBmi.getWeight())
            );
        }

        @Test
        void shouldReturnNullWhenCoderListEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            // when
            Coder coderWorstBmi = BMICalculator.findCoderWithWorstBMI(coders);
            // then
            // assertions that have a relation, can be grouped together with assertAll
            assertNull(coderWorstBmi);
        }

        @Test
        void shouldReturnCorrectBMIScoreArrayWhenCoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.8, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expected = {18.52, 29.59, 19.53};
            // when
            double[] bmiScores = BMICalculator.getBMIScores(coders);
            // then
            // arrays are reference types and so it cannot be compared with equals
            assertArrayEquals(expected, bmiScores);
            assertEquals(expected[0], bmiScores[0]);
            assertNotSame(expected, bmiScores);
        }
    }

    @Nested
    @DisplayName("Parametrized tests")
    class ParametrizedTests {

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = {"89.0, 1.72", "95, 1.75", "110.5, 1.78"})
        void parametrizedTests(Double coderWeight, Double coderHeight) {
            assertTrue(BMICalculator.isDietRecommended(coderWeight, coderHeight));
        }

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void actualCsvFileForParametrizedTests(Double coderWeight, Double coderHeight) {
            assertTrue(BMICalculator.isDietRecommended(coderWeight, coderHeight));
        }
    }

    @Nested
    @DisplayName("Performance testing")
    class PerformanceTests {

        @Test
        void performanceTestInLestThan50Milliseconds() {
            // given
            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }
            // when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
            // then
            // assertTimeout only checks how long the executable took (nothing from the given part)
            assertTimeout(Duration.ofMillis(50), executable);
        }

        @Test
        @DisplayName("BMICalculator.findCoderWithWorstBMI should be done in 10 milliseconds (just for production)")
        void performanceTestShouldRunOnlyInProdEnvironment() {
            // we can use assume to determine if a test should run. In this case we are simulating a dev machine but
            // this performance test should run only in production
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            // assumeTrue does not pass, this test will be skipped

            // given
            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }
            // when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
            // then
            // assertTimeout only checks how long the executable took (nothing from the given part)
            assertTimeout(Duration.ofMillis(10), executable);
        }
    }
}