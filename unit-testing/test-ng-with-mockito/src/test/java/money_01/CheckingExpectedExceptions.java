package money_01;

import org.testng.annotations.Test;

@Test
public class CheckingExpectedExceptions {

    @Test(expectedExceptions = Exception.class)
    public void shouldThrowAnException() {

    }
}
