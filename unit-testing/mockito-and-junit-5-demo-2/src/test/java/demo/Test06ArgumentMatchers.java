package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test06ArgumentMatchers {

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
    void makeBookingShouldNotCompleteBookingWhenPriceTooHigh() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, true);
        when(this.paymentServiceMock.pay(any(BookingRequest.class), anyDouble()))
                .thenThrow(BusinessException.class);

        when(this.paymentServiceMock.pay(any(BookingRequest.class), eq(400)))
                .thenThrow(BusinessException.class);
        // when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);
        // then
        assertThrows(BusinessException.class, executable);
    }

    @Test
    @Disabled
    void makeBookingShouldNotCompleteBookingWhenPriceTooHighUsingCombinationArgMatchers() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, true);
        // argument matchers cannot be combined with hardcoded primitives. We will need to use eq() in those cases.
        when(this.paymentServiceMock.pay(any(BookingRequest.class), eq(400)))
                .thenThrow(BusinessException.class);
        // when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);
        // then
        assertThrows(BusinessException.class, executable);
    }
}
/*
there is an anyString() argument matcher, but it will not work for null strings. For that case we should use any()
as for a normal object.
 */