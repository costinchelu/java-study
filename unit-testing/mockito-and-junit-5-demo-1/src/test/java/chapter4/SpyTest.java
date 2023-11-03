package chapter4;

import chapter4.dto.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SpyTest {

    @Test
    void spyTest() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);


        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    @Test
    void usingDoReturnTest() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        // when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        System.out.println(spy.get(0));
        verify(spy).get(0);
    }

    /*
    Mockito spy object allows us to use real objects instead of mocks by replacing some methods with the stubbed ones.
    Once an expectation is set for a method on a spy object, then spy no longer returns the original value.
    It starts returning the stubbed value, but still it exhibits the original behavior for the other methods that are not stubbed.
    Mockito can create a spy of a real object. Unlike stubbing, when we use spy, the real methods are called
    (unless a method was stubbed).
    Spy is also known as partial mock; one example of the use of spy in the real world is dealing with legacy code.
     */
    @Test
    void spying() {
        Stock realStock = new Stock("A", "Company A", BigDecimal.ONE);
        Stock spyStock = spy(realStock);

        //call real method from spy
        assertEquals("A", spyStock.getSymbol());

        //Changing value using spy
        spyStock.updatePrice(BigDecimal.ZERO);

        //verify spy has the changed value
        assertEquals(BigDecimal.ZERO, spyStock.getPrice());


        //Stubbing method
        when(spyStock.getPrice()).thenReturn(BigDecimal.TEN);

        //Changing value using spy
        spyStock.updatePrice(new BigDecimal("7"));

        //Stubbed method value 10.00 is returned NOT 7
        assertNotEquals(new BigDecimal("7"), spyStock.getPrice());

        //Stubbed method value 10.00
        assertEquals(BigDecimal.TEN, spyStock.getPrice());
    }
}
