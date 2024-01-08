package behavioral.facade;

import lombok.ToString;


class Airline {

    private String name;

    public Airline(String name) {
        this.name = name;
    }

    public Flight bookFlight(String departure, String arrival) {
        System.out.println("Book flight from " + departure + " to " + arrival);
        return new Flight(this, departure, arrival);
    }
}



record Flight(Airline airline, String departure, String arrival) {}



@ToString
class Hotel {

    private final String name;

    public Hotel(String name) {
        this.name = name;
    }

    public void bookRoom(String city) {
        System.out.println("Book room in " + city + ", Hotel " + name);
    }
}



class AirlineAndHotelFacade {

    public static void bookAccommodationAndTransportPackage(String departure, String arrival, String hotelName) {
        Airline airline = new Airline("Air Lines");
        Hotel hotel = new Hotel(hotelName);

        Flight to = airline.bookFlight(departure, arrival);
        hotel.bookRoom(arrival);
        Flight from = airline.bookFlight(arrival, departure);

        System.out.println(to + " -> " + hotel + " <- " + from);
    }
}



public class Facade {

    public static void main(String[] args) {
        AirlineAndHotelFacade.bookAccommodationAndTransportPackage("Bucharest", "Paris", "Ritz");
    }
}
