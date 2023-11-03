package chapter_04.wrong.taxes;

import common.personnel.FullTimeEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTest {

    private TaxCalculator taxCalculator;

    @BeforeEach
    public void setUp() {
        taxCalculator = new TaxCalculator();
    }

    @Test
    public void testCalculateFull() {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("Fanel Fotescu", 2000);
        double rezultatAsteptat = 620;
        double rezultatPrimit = taxCalculator.calculate(fullTimeEmployee);
        assertEquals(rezultatAsteptat, rezultatPrimit, 0.01);
    }

}