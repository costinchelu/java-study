package car_02;

import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

@Test
public class FirstMockitoTest {
    private Car myFerrari = mock(Car.class);

    public void testIfCarIsACar() {
        assertTrue(myFerrari instanceof Car);
    }
}