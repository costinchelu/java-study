package chapter4;

import chapter4.dto.Stock;
import chapter4.trading.MarketWatcher;
import chapter4.trading.Portfolio;
import chapter4.trading.StockBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArgumentCaptorTest {

    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;

    @InjectMocks
    StockBroker broker;

    @Captor
    ArgumentCaptor<String> stockIdCaptor;

    @Captor
    ArgumentCaptor<Stock> stockCaptor;

    @Captor
    ArgumentCaptor<Integer> stockSellCountCaptor;

    @Test
    void argument_captor() throws Exception {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal("11.20"));
        when(marketWatcher.getQuote(anyString())).thenReturn(aCorp);

        broker.perform(portfolio, aCorp);

        verify(marketWatcher).getQuote(stockIdCaptor.capture());
        assertEquals("A", stockIdCaptor.getValue());

        //Two arguments captured
        verify(portfolio).sell(stockCaptor.capture(), stockSellCountCaptor.capture());
        assertEquals("A", stockCaptor.getValue().getSymbol());
        assertEquals(10, stockSellCountCaptor.getValue().intValue());
        /*
        Check that ArgumentCaptor takes a Class type in the forClass method and then the captor is passed to
        the verify method to collect the argument details.
        The sell method takes two arguments, Stock and Integer.
        So, two ArgumentCaptors are created.
        The stockCaptor object captures the Stock argument and stockSellCountCaptor captures the stock quantity.
         */
    }
}
