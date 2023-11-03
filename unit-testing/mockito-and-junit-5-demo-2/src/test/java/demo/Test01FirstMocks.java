package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Test01FirstMocks {

    // class in test:
    private BookingService bookingService;

    // mocks:
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Mock
    private BookingDAO bookingDAOMock;
    @Mock
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void shouldCalculateCorrectPriceWhenCorrectInput() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, false);
        double expectedPrice = 2 * 2 * 50;
        // when
        double actualPrice = bookingService.calculatePrice(bookingRequest);
        // then
        assertEquals(expectedPrice, actualPrice);
    }
}