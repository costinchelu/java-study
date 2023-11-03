package chapter4;

import chapter4.dto.Stock;
import chapter4.trading.MarketWatcher;
import chapter4.trading.Portfolio;
import chapter4.trading.StockBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockBrokerCustomArgumentMatcherTest {

    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;

    @InjectMocks
    StockBroker broker;

    @BeforeEach
    void setUp() {
        broker = new StockBroker(marketWatcher);
    }

    @Test
    void argument_matcher() {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));

        Stock blueChipStock = new Stock("FB", "FB Corp", new BigDecimal("1000.00"));
        Stock otherStock = new Stock("XY", "XY Corp", new BigDecimal("5.00"));

        when(marketWatcher.getQuote(argThat(new BlueChipStockMatcher()))).thenReturn(blueChipStock);
        when(marketWatcher.getQuote(argThat(new OtherStockMatcher()))).thenReturn(otherStock);

        broker.perform(portfolio, blueChipStock);
        verify(portfolio).sell(blueChipStock,10);

        broker.perform(portfolio, otherStock);
        verify(portfolio, never()).sell(otherStock,10);
    }

    static class BlueChipStockMatcher implements ArgumentMatcher<String> {

        @Override
        public boolean matches(String symbol) {
            List<String> symbols =
                    Stream.of("FB", "AAPL")
                            .filter(el -> el.equals(symbol))
                            .collect(Collectors.toList());
            return !symbols.isEmpty();
//            return "FB".equals(symbol) || "AAPL".equals(symbol);
        }
    }

    static class OtherStockMatcher extends BlueChipStockMatcher {

        @Override
        public boolean matches(String symbol) {
            return !super.matches(symbol);
        }
    }
}
