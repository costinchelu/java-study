package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test03CustomReturnValues {

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
    void shouldCountAvailablePlacesWhenOneRoomAvailable() {
        // given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room 1", 2)));
        int expectedCapacity = 2;
        // when
        int actualCapacity = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expectedCapacity, actualCapacity);
    }

    @Test
    void shouldCountAvailablePlacesWhenMultipleRoomsAvailable() {
        // given
        List<Room> rooms = List.of(new Room("Room 1", 2), new Room("Room 2", 3));
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(rooms);
        int expectedCapacity = 5;
        // when
        int actualCapacity = bookingService.getAvailablePlaceCount();
        // then
        assertEquals(expectedCapacity, actualCapacity);
    }
}