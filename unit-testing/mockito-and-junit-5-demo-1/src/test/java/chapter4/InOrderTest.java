package chapter4;

import chapter4.dto.Stock;
import chapter4.trading.MarketWatcher;
import chapter4.trading.Portfolio;
import chapter4.trading.StockBroker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
@DisplayName("Generic InOrder test")
class InOrderTest {

    @Mock
    private List<String> singleMock;

    @Mock
    private List<String> firstMock;

    @Mock
    private List<String> secondMock;

    @Test
    void singleMockInOrderTest() {
        // A. Single mock whose methods must be invoked in a particular order
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");
    }

    @Test
    void multipleMocksInOrderTest() {
        // B. Multiple mocks that must be used in a particular order
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");
    }

    // A + B can be mixed together at will

    @Nested
    @DisplayName("InOrder for stock example")
    class InOrderStockTest {

        @Mock
        MarketWatcher marketWatcher;

        @Mock
        Portfolio portfolio;

        @InjectMocks
        StockBroker broker;

        @Test
        void inorder() throws Exception {
            Stock aCorp = new Stock("A", "A Corp", new BigDecimal("11.20"));
            portfolio.getAvgPrice(aCorp);
            portfolio.getCurrentValue();
            marketWatcher.getQuote("X");
            portfolio.buy(aCorp);

            InOrder inOrder = inOrder(portfolio, marketWatcher);

            inOrder.verify(portfolio).getAvgPrice(isA(Stock.class));
            inOrder.verify(portfolio).getCurrentValue();
            inOrder.verify(marketWatcher).getQuote(anyString());
            inOrder.verify(portfolio).buy(isA(Stock.class));
        }
    }
}
