package lesson_20;

class CustomThread implements Runnable {

    private int maxNumber;
    private Printer printer;
    private boolean isEven;

    public CustomThread(int maxNumber, Printer printer, boolean isEven) {
        this.maxNumber = maxNumber;
        this.printer = printer;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        int number = isEven ? 2 : 1;
        while (number <= maxNumber) {
            if (isEven) {
                printer.printEven(number);
            } else {
                printer.printOdd(number);
            }

            number += 2;
        }
    }
}


class Printer {

    private volatile boolean oddPrinted;

    public synchronized void printOdd(int number) {
        while (oddPrinted) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);

        oddPrinted = true;
        notify();
    }

    public synchronized void printEven (int number) {
        while (!oddPrinted) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);

        oddPrinted = false;
        notify();
    }
}



public class EvenOddProblem {

    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread t1 = new Thread(new CustomThread(20, printer, false), "ODD");
        Thread t2 = new Thread(new CustomThread(20, printer, true), "EVEN");

        t1.start();
        t2.start();
    }
}
