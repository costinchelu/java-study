package sec18b_unittesting1;

import static org.junit.Assert.*;

public class BankAccountTest {

    @org.junit.Test
    public void deposit() {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
        // delta = approximation in the result. In this case we want the value of balance to be exactly 1200
        // we can use delta for cases when we get something like 1999.9999999 (sometimes when we're working with doubles)
    }

    @org.junit.Test
    public void withdraw() {
        BankAccount bankAccount = new BankAccount("Tim", "Buchalka",  1300.00, BankAccount.CHECKING);
        double balance = bankAccount.withdraw(400.00, true);
        assertEquals(900.00, balance, 0);
    }

    @org.junit.Test
    public void getBalance_deposit() {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void getBalance_withdraw() {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void isChecking_true() {
        BankAccount account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        assertTrue("Some message in case test fails", account.isChecking());
    }

}

