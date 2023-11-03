package client_04;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


@Test
public class ClientTestUsingTestDouble {
    private final static Phone MOBILE_PHONE = mock(Phone.class);
    private final static Phone STATIONARY_PHONE = mock(Phone.class);

    private Client client;

    @BeforeMethod
    public void setUp() {
        client = new Client();
    }

    public void shouldReturnTrueIfClientHasMobile() {
        when(MOBILE_PHONE.isMobile()).thenReturn(true);

        client.addPhone(MOBILE_PHONE);
        client.addPhone(STATIONARY_PHONE);

        assertTrue(client.hasMobile());
    }

    public void shouldReturnFalseIfClientHasNoMobile() {
        client.addPhone(STATIONARY_PHONE);

        assertFalse(client.hasMobile());
    }
}
