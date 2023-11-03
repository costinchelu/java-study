package chapter4;

import chapter4.dto.Stock;
import chapter4.trading.MarketWatcher;
import chapter4.trading.Portfolio;
import chapter4.trading.StockBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockBrokerTest {

    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;

    @Mock
    Stock stock;

    @InjectMocks
    StockBroker broker;

//    @BeforeEach
//    void setUp() {
//        broker = new StockBroker(marketWatcher);
//     works well with @InjectMocks too (because the SUT has constructor injection)
//    }

    @Test
    void marketWatcherReturnsCurrentStockStatus() {
        Stock uvsityCorp = new Stock("UV", "Uvsity Corporation", new BigDecimal("100.00"));

        when(marketWatcher.getQuote(anyString())).thenReturn(uvsityCorp);

        assertNotNull(marketWatcher.getQuote("UV"));
        assertThat(marketWatcher.getQuote("UV")).isNotNull();
    }

    @Test
    void whenTenPercentGainThenTheStockIsSold() {
        //Portfolio's getAvgPrice is stubbed to return $10.00
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));

        //A stock object is created with current price $11.20
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal("11.20"));

        //getQuote method is stubbed to return the stock
        when(marketWatcher.getQuote(anyString())).thenReturn(aCorp);

        //perform method is called, as the stock price increases by 12% the broker should sell the stocks
        broker.perform(portfolio, aCorp);

        //verifying that the broker sold the stocks
        verify(portfolio).sell(aCorp, 10);
    }


    @Test
    void verifyNoMoreInteraction() {
        Stock noStock = null;
        portfolio.getAvgPrice(noStock);
        //portfolio.sell(null, 0);

        verify(portfolio).getAvgPrice(eq(noStock));

        //this will fail if the sell method was invoked
        verifyNoMoreInteractions(portfolio);
        /*
        method checks whether any of the given mocks has any unverified interaction. We can use this method after
        verifying a mock method to make sure that nothing else was invoked on the mock.
         */
    }

    @Test()
    void throwsException() {
        // isA(SomeClass.class) and any(SomeClass.class) have the same effect
//        when(portfolio.getAvgPrice(isA(Stock.class))).thenThrow(new IllegalStateException("Database down"));
        when(portfolio.getAvgPrice(any(Stock.class))).thenThrow(new IllegalStateException("Database down"));

        assertThrows(IllegalStateException.class, () -> portfolio.getAvgPrice(stock));
    }

    @Test()
    void throwsExceptionVoidMethods() {
        doThrow(new IllegalStateException()).when(portfolio).buy(isA(Stock.class));

        assertThrows(IllegalStateException.class, () -> portfolio.buy(stock));
    }

    @Test
    void consecutiveCalls() {
        Stock stock = new Stock(null, null, null);
        when(portfolio.getAvgPrice(stock))
                .thenReturn(BigDecimal.TEN)
                .thenReturn(BigDecimal.ZERO)
                .thenThrow(new IllegalStateException());

        assertAll(
                () -> assertEquals(BigDecimal.TEN, portfolio.getAvgPrice(stock)),
                () -> assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(stock)),
                () -> assertThrows(IllegalStateException.class, () -> portfolio.getAvgPrice(stock))
        );
    }

}
/*
• thenReturn(value to be returned): This returns a specific value.

• thenThrow(throwable to be thrown): This throws a specific exception.

• thenAnswer(Answer answer):
    - Unlike returning a specific value, some logic is executed and an action is taken from that logic;
    for example, some value is computed and returned.
    - Answer is an interface.

• thenCallRealMethod():
    - This calls the real method on the object.
    - The real method doesn't return any default value.
    - It performs the actual logic, but if it needs to invoke any method that is stubbed,
            then the stubbed value is passed to the real method;
            for example, the foo()method calls bar(), but bar() is stubbed to return a value 10, so foo() will get 10.
 */