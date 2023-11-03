package sec15o_challenge_123;

public class BankAccount {

    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // CHALLENGE 2 = there are 2 ways to set synchronized:
    // 1st solution (synchronized method)
//    public synchronized void deposit(double amount) {
//        balance += amount;
//    }
//
//    public synchronized void withdraw(double amount) {
//        balance -= amount;
//    }

    // 2nd solution (synchronized block)
    // we would want to set synchronized on the smallest amount of code possible to minimize the performance cost
    public void deposit(double amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        synchronized (this) {
            balance -= amount;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }

    public double getBalance() {
        return balance;
    }
}

/*
*
* CHALLENGE 1
* We need a bank account that can be accessed by 2 users in the same time
* CHALLENGE 2
* Also we need to make the class ThreadSafe (synchronized)
*
 */