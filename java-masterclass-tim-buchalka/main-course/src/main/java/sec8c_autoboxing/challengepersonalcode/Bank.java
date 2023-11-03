package sec8c_autoboxing.challengepersonalcode;

// There should be a Bank class
// It should have an arraylist of Branches

// Bank:
// Add a new branch
// Add a customer to that branch with initial transaction
// Add a transaction for an existing customer for that branch
// Show a list of customers for a particular branch and optionally a list
// of their transactions
// Demonstration autoboxing and unboxing in your code

import java.util.ArrayList;

public class Bank {
    private ArrayList<Branch> branches;

    public Bank() {
        branches = new ArrayList<>();
    }

    public ArrayList<Branch> getBranches() { return branches; }

    public void addNewBranch(Branch b) {
        branches.add(b);
    }

    public void addCustomerToBranch(String name, double transaction, Branch b) {
        b.addCustomer(name, transaction);
    }

    public void addTransactionToExistingCustomer(String name, double transaction, Branch b) {
        b.addTransactionsExistingCustomer(name, transaction);
    }

    public void printBranch(Branch b) {
        ArrayList<Customer> c = b.getCustomers();
        for(int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).getName());
            System.out.println(c.get(i).getTransactions().toString());
        }
    }
}
