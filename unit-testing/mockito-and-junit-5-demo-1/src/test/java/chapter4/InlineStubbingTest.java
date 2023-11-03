package chapter4;

import chapter4.dto.Stock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class InlineStubbingTest {

    Stock globalStock = when(Mockito.mock(Stock.class).getPrice()).thenReturn(BigDecimal.ONE).getMock();

    @Test
    void access_global_mock() {
        assertEquals(BigDecimal.ONE, globalStock.getPrice());
    }
}
