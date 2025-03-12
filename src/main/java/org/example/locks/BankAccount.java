package org.example.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    private Lock lock = new ReentrantLock();

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " wants to withdraw " + amount);
        try {
            // lock.lock(); continuous tries to get lock
            // lock.tryLock(); tries to get lock for specified time
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(1000); // indicates some large task is going on
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " has withdrawn " + amount);
                    }
                    catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }
                }
                else{
                    System.out.println(Thread.currentThread().getName() + " does not have enough balance");
                }
            }
            else{
                System.out.println(Thread.currentThread().getName() + " Could not get lock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
//    public void withdraw(int amount) {
//        System.out.println(Thread.currentThread().getName() + " wants to withdraw " + amount);
//        if (balance >= amount) {
//            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
//            try {
//                Thread.sleep(1000); // indicates some large task is going on
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//            balance -= amount;
//            System.out.println(Thread.currentThread().getName() + " has withdrawn " + amount);
//        }
//        else{
//            System.out.println(Thread.currentThread().getName() + " does not have enough balance");
//        }
//    }
}
