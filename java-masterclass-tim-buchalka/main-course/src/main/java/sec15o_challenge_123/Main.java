package sec15o_challenge_123;

public class Main {
    public static void main(String[] args) {

        // starting account
        BankAccount account = new BankAccount("12345-678", 1000.00);

        // CHALLENGE 1
        // this simulates 2 users that accesses the same account and make some operations on it (concurrently)
        // 1st method
//        Thread trThread1 = new Thread() {
//            public void run() {
//                account.deposit(300.00);
//                account.withdraw(50.00);
//            }
//        };
//
//        Thread trThread2 = new Thread() {
//            public void run() {
//                account.deposit(203.75);
//                account.withdraw(100.00);
//            }
//        };

        // 2nd method uses the runnable interface
        Thread trThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300.00);
                account.withdraw(50.00);
            }
        });

        Thread trThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100.00);
            }
        });

        trThread1.start();
        trThread2.start();

        System.out.println("Balance = " + account.getBalance());
    }
}
