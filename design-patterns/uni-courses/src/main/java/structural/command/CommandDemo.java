package structural.command;

public class CommandDemo {

    public static void main(String[] args) {
        Operator op = new Operator();

        TravelPackage accommodation = new Accommodation(58);
        TravelPackage transport = new Accommodation(12);

        Command bookAccommodation = new Booking(accommodation);
        Command sellAccommodation = new Selling(accommodation);
        Command bookTransport = new Booking(transport);
        Command sellTransport = new Selling(transport);

        op.invoke(bookAccommodation);
        op.invoke(sellAccommodation);
        op.invoke(bookTransport);
        op.invoke(sellTransport);
    }
}
