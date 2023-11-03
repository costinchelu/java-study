package money_01;

import org.testng.annotations.DataProvider;

public class DataProviders {
    // parametrized testing
    @DataProvider
    public static Object[][] getMoney() {
        return new Object[][] { { 10, "USD" }, { 20, "EUR" } };
    }

}
