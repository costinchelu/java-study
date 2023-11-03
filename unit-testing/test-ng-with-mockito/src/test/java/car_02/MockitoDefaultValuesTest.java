package car_02;

import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class MockitoDefaultValuesTest {

   private Car myFerrari = mock(Car.class);

   @Test
   public void testDefaultBehaviourOfTestDouble() {
      //default
      assertFalse(myFerrari.needsFuel(),
              "new test double should by default return false as boolean");

      //after setting behaviour
      when(myFerrari.needsFuel()).thenReturn(true);
      assertTrue(myFerrari.needsFuel(),
              "new test double should by default return false as boolean");

      assertEquals(myFerrari.getEngineTemperature(), 0.0,
              "new test double should by default return 0.0 as double");

      when(myFerrari.getEngineTemperature()).thenReturn(85.5);
      assertEquals(myFerrari.getEngineTemperature(), 85.5,
              "new test double should by default return 0.0 as double");
   }

   @Test(expectedExceptions = RuntimeException.class)
   public void throwException() {
      when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
      myFerrari.needsFuel();
   }

   @Test
   public void testVerification() {
      myFerrari.driveTo("Sweet home Alabama");
      myFerrari.needsFuel();

      verify(myFerrari).driveTo("Sweet home Alabama");
      verify(myFerrari).needsFuel();
   }
}