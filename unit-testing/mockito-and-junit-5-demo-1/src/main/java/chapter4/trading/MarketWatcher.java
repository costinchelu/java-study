package chapter4.trading;

import chapter4.dto.Stock;

public interface MarketWatcher {

    Stock getQuote(String symbol);
}
