package problems.concurrency;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class ThreadsEvenOdd implements Runnable {

    private int max;
    private Printer printer;
    private boolean isEvenNumber;

    @Override
    public void run() {
        int number = isEvenNumber ? 2 : 1;
        while (number <= max) {
            if (isEvenNumber) {
                printer.printEven(number);
            } else {
                printer.printOdd(number);
            }

            number += 2;
        }
    }
}


class Printer {

    private volatile boolean isOdd;   // boolean default value is false

    // this will run first (and then will not enter in while)
    public synchronized void printOdd(int number) {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);
        isOdd = true;

        notify();
    }


    public synchronized void printEven(int number) {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);
        isOdd = false;
        notify();
    }
}


class Demo {

    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread t1 = new Thread(new ThreadsEvenOdd(20, printer, false), "Odd");
        Thread t2 = new Thread(new ThreadsEvenOdd(20, printer, true), "Even");

        t1.start();
        t2.start();
    }
}