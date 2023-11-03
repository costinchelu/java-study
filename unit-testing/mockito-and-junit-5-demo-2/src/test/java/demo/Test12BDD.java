package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test12BDD {

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
    void shouldCountAvailablePlacesWhenOneRoomAvailable() {
        // given
        given(this.roomServiceMock.getAvailableRooms())
                .willReturn(Collections.singletonList(new Room("Room 1", 2)));
        int expectedCapacity = 2;
        // when
        int actualCapacity = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expectedCapacity, actualCapacity);
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
        then(paymentServiceMock).should(times(1)).pay(bookingRequest, 200);
        verifyNoMoreInteractions(paymentServiceMock);
    }
}