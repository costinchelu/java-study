package chapter4;

import chapter4.dto.Stock;
import chapter4.trading.Portfolio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockBrokerUsingAnswerTest {

    @Mock
    Portfolio portfolio;

    Map<String, List<Stock>> stockMap = new HashMap<>();

    /*
    We are using a mock Portfolio instance, so we cannot return the total stock value.
    We can fix this using Answer and make the test totally configurable.
    When a new stock is bought, we will store the stock in HashMap, and when the getCurrentValue method will be invoked,
    we will compute the value dynamically from HashMap.
    So, we need two Answer objects, one to store stocks and the other to compute the total.

    The getCurrentValue() method will be stubbed to return the preceding answer implementation.
     */

    @Test
    void answering() {
        stockMap.clear();

        doAnswer(new BuyAnswer()).when(portfolio).buy(isA(Stock.class));
        when(portfolio.getCurrentValue()).then(new TotalPriceAnswer());

        portfolio.buy(new Stock("A", "A", BigDecimal.TEN));
        portfolio.buy(new Stock("B", "B", BigDecimal.ONE));
        portfolio.buy(new Stock("B", "B", BigDecimal.TEN));
        portfolio.buy(new Stock("C", "C", BigDecimal.valueOf(15.5)));
        BigDecimal sum = BigDecimal.TEN.add(BigDecimal.ONE).add(BigDecimal.TEN).add(BigDecimal.valueOf(15.5));

        assertEquals(sum, portfolio.getCurrentValue());
    }

    class BuyAnswer implements Answer<Object> {

        @Override
        public Object answer(InvocationOnMock invocationOnMock) {
            Stock newStock = (Stock) invocationOnMock.getArguments()[0];
            List<Stock> stocks = stockMap.get(newStock.getSymbol());
            if (stocks != null) {
                stocks.add(newStock);
            } else {
                stocks = new ArrayList<>();
                stocks.add(newStock);
                stockMap.put(newStock.getSymbol(), stocks);
            }
            return null;
        }
    }

    class TotalPriceAnswer implements Answer<BigDecimal> {

        @Override
        public BigDecimal answer(InvocationOnMock invocationOnMock) {
            BigDecimal sum = BigDecimal.ZERO;
            for (String stockId : stockMap.keySet()) {
                for (Stock stock : stockMap.get(stockId)) {
                    sum = sum.add(stock.getPrice());
                }
            }
            return sum;
        }
    }
}
