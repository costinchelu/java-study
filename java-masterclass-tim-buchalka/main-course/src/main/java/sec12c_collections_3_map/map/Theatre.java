package sec12c_collections_3_map.map;

/*
The theatre has a number of rows noted with a letter of the alphabet starting with A
Every row has a number of seats noted with an integer starting with 1
Example:
place B9 = second row, 9th seat


List -> Collection (more generic)
List -> LinkedList -> HashSet -> LinkedHashSet ->

example:
private Collection<Seat> seats = new LinkedList<>();

to sort elements of a collection we can use the interface Comparable or a Comparator. With a Comparator
we have the advantage that we can assign returns to different comparisons. We can have multiple Comparators.


 */

import java.util.*;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if(seat1.getPrice() < seat2.getPrice()) {
                return -1;
            }
            else if(seat1.getPrice() > seat2.getPrice()) {
                return 1;
            }
            else {
                return 0;
            }
        }
    };


    static final Comparator PLACE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if(seat1.compareTo(seat2) == 1) {
                return 1;
            }
            else if(seat2.compareTo(seat1) == -1) {
                return -1;
            }
            else {
                return 0;
            }
        }
    };

    public Theatre(String theatreName, int numRows, int seatPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows - 1);
        for(char row = 'A'; row <= lastRow; row++) {
            for(int seatNum = 1; seatNum <= seatPerRow; seatNum++) {
                double price = 12.00;

                if((row < 'D') && (seatNum >= 4 && seatNum <= 9)) {
                    price = 14.00;
                }
                else if((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }

                Seat seat = new Seat(row + String.format("%02d", seatNum), price );
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if(foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        }
        else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }


// for sequential search:
//        for(Seat seat : seats) {
//            if(seat.getSeatNumber().equals(seatNumber)) {
//                requestedSeat = seat;
//                break;
//            }
//        }
//
//        if(requestedSeat == null) {
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//        return requestedSeat.reserve();
    }

    //for testing (prints all seats)
    public Collection<Seat> getSeats() {
        return seats;
    }




    //inner class:
    public class Seat implements Comparable<Seat> {
        private double price;
        private final String seatNumber;
        private boolean reserved = false;   //initially, the seat is free and can be reserved

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            }
            else {
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareTo(seat.getSeatNumber());
        }
        //we use compareTo for implementing binary search which is more efficient than sequential search
    }




}





