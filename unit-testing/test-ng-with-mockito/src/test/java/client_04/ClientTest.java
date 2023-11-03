package client_04;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class ClientTest {
    private final String ANY_NUMBER = "999-888-777";
    private final Phone MOBILE_PHONE = new Phone(ANY_NUMBER, true);
    private final Phone STATIONARY_PHONE = new Phone(ANY_NUMBER, false);

    private Client client;

    @BeforeMethod
    public void setUp() {
        client = new Client();}

    public void shouldReturnTrueIfClientHasMobile() {
        client.addPhone(MOBILE_PHONE);
        client.addPhone(STATIONARY_PHONE);

        assertTrue(client.hasMobile());

    }

    public void shouldReturnFalseIfClientHasNoMobile() {
        client.addPhone(STATIONARY_PHONE);

        assertFalse(client.hasMobile());
    }
}