package structural.command;

import lombok.AllArgsConstructor;

// receiver
public interface TravelPackage {

    void sell();

    void book();
}

@AllArgsConstructor
class Accommodation implements TravelPackage {

    private int code;

    @Override
    public void sell() {
        System.out.println("Accommodation package code " + code + " is sold.");
    }

    @Override
    public void book() {
        System.out.println("Accommodation Package code " + code + " is booked.");
    }
}

@AllArgsConstructor
class Transport implements TravelPackage {

    private int code;

    @Override
    public void sell() {
        System.out.println("Travel package code " + code + " is sold.");
    }

    @Override
    public void book() {
        System.out.println("Travel Package code " + code + " is booked.");
    }
}