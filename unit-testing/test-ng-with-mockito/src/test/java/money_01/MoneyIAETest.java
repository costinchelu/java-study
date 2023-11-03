package money_01;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MoneyIAETest {

   private final static int VALID_AMOUNT = 5;

   private final static String VALID_CURRENCY = "USD";

   @DataProvider
   private static Object[][] getInvalidAmount() {
      return new Integer[][] { { -12387 }, { -5 }, { -1 } };
   }

   @Test(dataProvider = "getInvalidAmount", expectedExceptions = {IllegalArgumentException.class, RuntimeException.class})
   public void shouldThrowIAEForInvalidAmount(int invalidAmount) {
      Money money = new Money(invalidAmount, VALID_CURRENCY);
   }

   @DataProvider
   private static Object[][] getInvalidCurrency() {
      return new String[][] { { null }, { "" } };
   }

   @Test(dataProvider = "getInvalidCurrency", expectedExceptions = IllegalArgumentException.class)
   public void shouldThrowIAEForInvalidCurrency(String invalidCurrency) {
      Money money = new Money(VALID_AMOUNT, invalidCurrency);
   }
}
