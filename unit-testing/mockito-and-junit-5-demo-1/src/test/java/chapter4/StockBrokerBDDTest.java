package chapter4;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import chapter4.dto.Stock;
import chapter4.trading.MarketWatcher;
import chapter4.trading.Portfolio;
import chapter4.trading.StockBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class StockBrokerBDDTest {

    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;

    StockBroker broker;

    @BeforeEach
    public void setUp() {
        broker = new StockBroker(marketWatcher);
    }

    @Test
    void should_sell_a_stock_when_price_increases_by_ten_percent() throws Exception {
        Stock aCorp = new Stock("FB", "FaceBook", new BigDecimal("11.20"));

        //Given a customer previously bought FB stocks at $10.00/per share
        given(portfolio.getAvgPrice(isA(Stock.class))).willReturn(new BigDecimal("10.00"));
        given(marketWatcher.getQuote("FB")).willReturn(aCorp);

        //when the FB stock price becomes $11.00
        broker.perform(portfolio, aCorp);

        //then the FB stocks  are sold
        verify(portfolio).sell(aCorp, 10);
    }
}
