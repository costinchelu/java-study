package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Test07TestingSideEffectsWithVerify {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void shouldInvokePaymentWhenPrepaid() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, true);
        // when
        bookingService.makeBooking(bookingRequest);
        // then
        verify(paymentServiceMock, times(1)).pay(bookingRequest, 200);
        verifyNoMoreInteractions(paymentServiceMock);
    }

    @Test
    void shouldNotInvokePaymentWhenNotPrepaid() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, false);
        // when
        bookingService.makeBooking(bookingRequest);
        // then
        verify(paymentServiceMock, never()).pay(any(), anyDouble());
    }
}