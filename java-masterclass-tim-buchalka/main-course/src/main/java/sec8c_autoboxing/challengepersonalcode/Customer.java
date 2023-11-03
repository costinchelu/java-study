package sec8c_autoboxing.challengepersonalcode;

// The Customer class should have an arraylist of Doubles (transactions)
// Customer:
// Name, and the ArrayList of doubles.

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name) {
        this.name = name;
        transactions = new ArrayList<Double>();
    }

    public String getName() { return name; }

    public ArrayList<Double> getTransactions() { return transactions; }


}
