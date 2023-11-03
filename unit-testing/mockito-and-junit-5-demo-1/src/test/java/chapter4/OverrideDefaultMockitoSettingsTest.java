package chapter4;

import chapter4.dto.Stock;
import chapter4.trading.Portfolio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OverrideDefaultMockitoSettingsTest {


    @Test
    void changingDefault() {
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal("11.20"));
        Portfolio pf = Mockito.mock(Portfolio.class);

        //default null is returned
        assertNull(pf.getAvgPrice(aCorp));
        Portfolio pf1 = Mockito.mock(Portfolio.class, Mockito.RETURNS_SMART_NULLS);

        //a smart null is returned
        System.out.println("#1 " + pf1.getAvgPrice(aCorp));
        assertNotNull(pf1.getAvgPrice(aCorp));
        Portfolio pf2 = Mockito.mock(Portfolio.class, Mockito.RETURNS_MOCKS);

        // a mock is returned
        System.out.println("#2 " + pf2.getAvgPrice(aCorp));
        assertNotNull(pf2.getAvgPrice(aCorp));
        Portfolio pf3 = Mockito.mock(Portfolio.class, Mockito.RETURNS_DEEP_STUBS);

        //a deep stubbed mock is returned
        System.out.println("#3 " + pf3.getAvgPrice(aCorp));
        assertNotNull(pf3.getAvgPrice(aCorp));
    }
}
