package demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeAll
    static void BeforeAll() {
        System.out.println("Once before all unit tests. Must be a static method");
    }

    @BeforeEach
    void setup() {
        dietPlanner = new DietPlanner(20, 30, 50);
    }

    @Test
    void shouldReturnCorrectDietPlanWhenCorrectCoder() {
        // given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expectedDietPlan = new DietPlan(2202, 110, 73, 275);
        // when
        DietPlan actualDietPlan = dietPlanner.calculateDiet(coder);
        // then
        assertAll(
                () -> assertEquals(expectedDietPlan.getCalories(), actualDietPlan.getCalories()),
                () -> assertEquals(expectedDietPlan.getProtein(), actualDietPlan.getProtein()),
                () -> assertEquals(expectedDietPlan.getFat(), actualDietPlan.getFat()),
                () -> assertEquals(expectedDietPlan.getCarbohydrate(), actualDietPlan.getCarbohydrate())
        );

        // diet plan is a reference type so assert equals on the objects will not work
        // assertEquals(expectedDietPlan, actualDietPlan);
    }

    // when we need a test to run multiple times (usually for random number generation)
    @RepeatedTest(5)
    void repeatedTest() {
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expectedDietPlan = new DietPlan(2202, 110, 73, 275);
        DietPlan actualDietPlan = dietPlanner.calculateDiet(coder);
        assertAll(
                () -> assertEquals(expectedDietPlan.getCalories(), actualDietPlan.getCalories()),
                () -> assertEquals(expectedDietPlan.getProtein(), actualDietPlan.getProtein()),
                () -> assertEquals(expectedDietPlan.getFat(), actualDietPlan.getFat()),
                () -> assertEquals(expectedDietPlan.getCarbohydrate(), actualDietPlan.getCarbohydrate())
        );
    }
}
/*
=====================================
           For JUnit 4:
=====================================

test methods should be public (explicitly) in JUnit 4
No @Nested and @RepeatedTest in JUnit4

@BeforeEach/AfterEach -> @Before/After
@BeforeAll/AfterAll -> @BeforeClass/AfterClass
@Disabled -> @Ignore

assertThrows(Exception.class, () -> {})   -> @Test(expected = {Exception.class})

assertTimeout(Duration, () -> {})   -> @Test(timeout = milliseconds)
 */