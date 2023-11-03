package money_01;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.assertNotNull;

public class LazyDataProviderTest {

   @DataProvider
   private Iterator<Object[]> getLazyData() {
      return new Iterator<>() {

         private int counter = 1;

         @Override
         public boolean hasNext() {
            return counter < 4;
         }

         @Override
         public Object[] next() {
            return new Object[] { new ComplexObject(counter++) };
         }

         @Override
         public void remove() { // not important }
         }

      };
   }

   @Test(dataProvider = "getLazyData")
   public void testLazyData(ComplexObject object) {
      System.out.println("lazy data: " + object);
      assertNotNull(object);
   }
}
