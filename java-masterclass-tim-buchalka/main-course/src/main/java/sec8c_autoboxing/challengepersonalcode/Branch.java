package sec8c_autoboxing.challengepersonalcode;

// Each Branch should have an arraylist of Customers
// Branch:
// Need to be able to add a new customer and initial transaction amount.
// Also needs to add additional transactions for that customer/branch

import java.util.ArrayList;

public class Branch {
    private ArrayList<Customer> customers;

    public Branch(String name, double initialTransaction) {
        customers = new ArrayList<>();
        Customer c = new Customer(name);
        c.getTransactions().add(initialTransaction);
        customers.add(c);
    }

    public ArrayList<Customer> getCustomers() { return customers; }

    public void addCustomer(String name, double transaction) {
        Customer c = new Customer(name);
        c.getTransactions().add(transaction);
        customers.add(c);
    }

    private int indexOfCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void addTransactionsExistingCustomer(String name, double transaction) {
        int position = indexOfCustomer(name);
        if(position >= 0) {
            customers.get(position).getTransactions().add(transaction);
            System.out.println("Added " + transaction + " to " + name);
        }
        else {
            System.out.println("Customer not found.");
        }
    }


}
