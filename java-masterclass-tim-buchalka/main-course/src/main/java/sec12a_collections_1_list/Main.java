package sec12a_collections_1_list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //1
        System.out.println("__TESTING RESERVE METHOD");
        Theatre theatre = new Theatre("Olympian", 8, 12);

        if(theatre.reserveSeat("D12")) {
            System.out.println("Proceed to payment\n");
        }
        else {
            System.out.println("Seat is already taken\n");
        }

        if(theatre.reserveSeat("B13")) {
            System.out.println("Proceed to payment\n");
        }
        else {
            System.out.println("Seat is already taken\n");
        }



        System.out.println("__SHALLOW COPY");
        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.getSeats());       //shallow copy
        printList(seatCopy);

    //to demonstrate that it is a shallow copy
    // (reserve in seatCopy and reservation is still present in theatre):
        seatCopy.get(1).reserve();
        if(theatre.reserveSeat("A02")) {
        System.out.println("Proceed to payment of seat A02\n");
        }
        else {
        System.out.println("Seat is already taken\n");
        }



        System.out.println("__SHUFFLE AND PRINTING SETS");
        //Collections.reverse(seatCopy);          //reversing elements of a list (in a deep copy)
        Collections.shuffle(seatCopy);            //shuffling elements of a list (random order)
        System.out.println("Printing seatCopy (copy of the initial list(shuffled):");
        printList(seatCopy);
        System.out.println("Printing theatre.seat (initial list ordered)");
        printList(theatre.getSeats());



        System.out.println("\n__MIN & MAX");
        Theatre.Seat minSeat = Collections.min(seatCopy);
        Theatre.Seat maxSeat = Collections.max(seatCopy);
        System.out.println("Min seat number is " + minSeat.getSeatNumber());
        System.out.println("Max seat number is " + maxSeat.getSeatNumber());



        System.out.println("\n__SORTLIST METHOD");
        sortList(seatCopy);
        System.out.println("Printing sorted seatCopy:");
        printList(seatCopy);



        System.out.println("\n__USING COMPARABLE");
        List<Theatre.Seat> reverseSeat = new ArrayList<>(theatre.getSeats());   //we grab the list of seats from theatre object
        Collections.reverse(reverseSeat);
        printList(reverseSeat);



        System.out.println("\n__USING A COMPARATOR");
        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);
        printList(priceSeats);
}

    public static void printList(Collection<Theatre.Seat> list) {
        for(Theatre.Seat seat : list) {
            System.out.print(seat.getSeatNumber() + " " + seat.getPrice() + "$  ");
        }
        System.out.println();
    }

    public static void sortList(List<? extends Theatre.Seat> list) {
        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = i + 1; j < list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) > 0) {
                    Collections.swap(list, i, j);
                }
            }
        }
    }

}
