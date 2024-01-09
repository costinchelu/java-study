package behavioral.proxy;


import lombok.AllArgsConstructor;

interface IBooking {

    void cancelBooking();
}


record Booking(int bookingCode, int nights) implements IBooking {

    @Override
    public void cancelBooking() {
        System.out.println("Booking no " + bookingCode + " is now cancelled.");
    }
}


@AllArgsConstructor
class BookingProxy implements IBooking {

    private Booking booking;

    @Override
    public void cancelBooking() {
        if (booking.nights() == 1) {
            booking.cancelBooking();
        } else {
            throw new RuntimeException("Booking no " + booking.bookingCode() + " cannot be cancelled (longer than 1 night).");
        }
    }
}


public class Proxy {

    public static void main(String[] args) {
        Booking booking1 = new Booking(1, 1);
        Booking booking2 = new Booking(2, 4);

        try {
            new BookingProxy(booking1).cancelBooking();
            new BookingProxy(booking2).cancelBooking();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
