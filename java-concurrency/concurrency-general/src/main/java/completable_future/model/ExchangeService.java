package completable_future.model;

public class ExchangeService {

    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

        private final double exchangeRate;

        Money(double exchangeRate) {
            this.exchangeRate = exchangeRate;
        }
    }

    public static double getExchangeRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(Money source, Money destination) {
        Util.delay();
        return destination.exchangeRate / source.exchangeRate;
    }

}
