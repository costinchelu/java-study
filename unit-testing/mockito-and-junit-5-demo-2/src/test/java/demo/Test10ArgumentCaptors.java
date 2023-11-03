package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Test10ArgumentCaptors {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    private ArgumentCaptor<Double> doubleCaptor;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

        this.doubleCaptor = ArgumentCaptor.forClass(Double.class);
    }

    @Test
    void shouldPayCorrectPriceWhenInputOK() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1",
                        LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3),
                        2,
                        true);
        // when
        bookingService.makeBooking(bookingRequest);
        // then
        // a capture cannot be combined with a non matcher value (in this case we will use eq())
        verify(paymentServiceMock, times(1)).pay(eq(bookingRequest), doubleCaptor.capture());

        double capturedArgument = doubleCaptor.getValue();
        assertEquals(200, capturedArgument);
    }

    @Test
    void shouldPayCorrectPriceWhenMultipleCalls() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1",
                        LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3),
                        2,
                        true);
        BookingRequest bookingRequest2 =
                new BookingRequest("1",
                        LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 2),
                        2,
                        true);

        List<Double> expectedValues = List.of(200.0, 100.0);
        // when
        bookingService.makeBooking(bookingRequest);
        bookingService.makeBooking(bookingRequest2);
        // then
        // a capture cannot be combined with a non matcher value (in this case we will use eq())
        verify(paymentServiceMock, times(2)).pay(any(), doubleCaptor.capture());

        List<Double> capturedArguments = doubleCaptor.getAllValues();

        assertAll(
                () -> assertEquals(expectedValues.get(0), capturedArguments.get(0)),
                () -> assertEquals(expectedValues.get(1), capturedArguments.get(1))
        );

        assertEquals(expectedValues, capturedArguments);
    }
}