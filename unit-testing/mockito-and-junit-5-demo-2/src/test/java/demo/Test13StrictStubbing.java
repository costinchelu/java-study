package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test13StrictStubbing {

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
    void shouldInvokePaymentWhenPrepaid() {
        // given
        BookingRequest bookingRequest =
                new BookingRequest("1", LocalDate.of(2021, 11, 1),
                        LocalDate.of(2021, 11, 3), 2, false);

        // we create a mock behaviour that will never be used (booking request is not prepaid
        // and so it will never call pay method on bookingService.makeBooking(bookingRequest) )
        // this will cause a Mockito exception (UnnecessaryStubbingException)
        // this feature (Strict Stubbing) helps maintain clean code
        // if we still need the behaviour and intent to ignore Strict Stubbing we can then use lenient()
        lenient().when(paymentServiceMock.pay(any(BookingRequest.class), anyDouble())).thenReturn("1");

        // when
        bookingService.makeBooking(bookingRequest);
        // then
        // no exception is thrown

    }
}