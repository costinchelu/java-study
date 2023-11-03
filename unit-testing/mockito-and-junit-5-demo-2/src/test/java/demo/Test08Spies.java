package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class Test08Spies {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOSpy;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOSpy = spy(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOSpy, mailSenderMock);
    }

    @Test
    void shouldInvokePaymentWhenPrepaid() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, true);
        // when
        String bookingId = bookingService.makeBooking(bookingRequest);
        // then
        verify(bookingDAOSpy).save(bookingRequest);
        System.out.println("bookingId = " + bookingId);
    }

    @Test
    void shouldCancelBookingWhenInputOK() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";

        // for spies:
        doReturn(bookingRequest).when(bookingDAOSpy).get(bookingId);
        bookingService.cancelBooking(bookingId);
    }
}
/*

Mock vs Spy

mock = dummy object with no real logic. Returns nice or custom values when asked

spy = use the real object logic (that we can modify)

 */