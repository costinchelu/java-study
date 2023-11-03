package sec8c_autoboxing.challengepersonalcode;

// You job is to create a simple banking application.

// Hint: Transactions
// Add data validation.
// e.g. check if exists, or does not exist, etc.
// Think about where you are adding the code to perform certain actions

public class Main {
    public static void main(String[] args) {
        Branch branch1 = new Branch("John", 430.5);
        Branch branch2 = new Branch("Sarah", 344.0);
        branch1.addCustomer("Clarice", 620.2);
        branch1.addCustomer("Tim", 1230.0);
        branch2.addCustomer("Michael", 120.0);
        branch1.addTransactionsExistingCustomer("Tim", 46.5);

        Bank bank1 = new Bank();
        bank1.addNewBranch(branch1);
        bank1.addNewBranch(branch2);

        bank1.addCustomerToBranch("Bill", 2300.0, branch2);
        bank1.addTransactionToExistingCustomer("Bill", 66.7, branch2);

        bank1.printBranch(branch1);
    }
}
