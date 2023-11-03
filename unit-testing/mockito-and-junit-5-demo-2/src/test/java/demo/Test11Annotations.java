package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test11Annotations {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Spy
    private BookingDAO bookingDAOSpy;
    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;

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
}