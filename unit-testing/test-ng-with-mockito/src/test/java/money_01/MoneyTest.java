package money_01;

import org.testng.annotations.Test;
import static org.testng.Assert.*;


@Test
public class MoneyTest {

   @Test(dataProvider = "getMoney", dataProviderClass = DataProviders.class)
   public void constructorShouldSetAmountAndCurrency(int amount, String currency) {
      Money money = new Money(amount, currency);

      assertEquals(money.getAmount(), amount);
      assertEquals(money.getCurrency(), currency);
   }

}
/*
 * assertTrue() Checks if the condition is true.
 * 
 * assertFalse() Checks if the condition is false.
 * 
 * assertNull() Checks if the object is null.
 * 
 * assertNotNull() Checks if the object is not null.
 * 
 * assertEquals() Uses the equals() method to verify that objects are identical.
 * 
 * assertNotEquals() Uses the equals() method to verify that objects are not
 * identical.
 * 
 * assertEqualsNoOrder() Checks if two arrays contain the same objects, but does
 * not care about the order[a]. [a] If you also want to verify the order, use
 * the assertEquals(Object[] actual, Object[] expected) method.
 * 
 * assertSame() Uses == to verify that objects are the same.
 * 
 * assertNotSame() Uses == to verify that objects are not the same.
 * 
 */