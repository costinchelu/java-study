package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class Test02DefaultReturnValues {

    // class in test:
    private BookingService bookingService;
    // dependencies for the Booking Service
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        // mocks:
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

        // by default, we get "nice mocks" (empty, but not null) so when we need some values from them, we can get default values
        System.out.println("List returned " + roomServiceMock.getAvailableRooms());
        // empty list
        System.out.println("Object returned " + roomServiceMock.findAvailableRoomId(null));
        // null objects
        System.out.println("Primitive returned " + roomServiceMock.getRoomCount());
        // 0 / false primitives
    }

    @Test
    void shouldCountAvailablePlaces() {
        // given

        double expectedPlaceCount = 0;
        // when
        int actualPlaceCount = bookingService.getAvailablePlaceCount();
        // getAvailablePlaceCount returns 0 by default
        // then
        assertEquals(expectedPlaceCount, actualPlaceCount);
    }
}