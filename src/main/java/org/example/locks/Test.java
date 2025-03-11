package org.example.locks;

public class Test {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        Runnable task = () -> {
            ba.withdraw(50);
        };

        Thread t1 = new Thread(task , "Thread 1");
        Thread t2 = new Thread(task , "Thread 2");
        t1.start();
        t2.start();

    }
}
