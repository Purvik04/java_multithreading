package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private Lock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        /*
        lock.lockInterruptibly();

        When a thread calls lock.lockInterruptibly(), it attempts to acquire the lock.
        Unlike the regular lock() method, if the thread is interrupted while waiting for the lock,
        it will throw an InterruptedException rather than waiting indefinitely.

        Internally, lock.lockInterruptibly() periodically checks the interrupt status of the thread while waiting.
        If an interrupt is detected before the lock is acquired, the method aborts and throws an exception.

        Lock Acquisition vs. Waiting:
        lock.lockInterruptibly() only makes the waiting phase interruptible.
        Once the lock is acquired, interrupts don’t cause the lock to be released automatically.
        */
        try{
            System.out.println("Outer Method");
            innerMethod();
        }
        finally {
            lock.unlock();
        }
    }

    public void innerMethod(){
        //no dedlock due to reentrant lock
        // java allows same thread to acquire lock multiple times
        lock.lock();
        try{
            System.out.println("Inner Method");
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo red = new ReentrantLockDemo();
        red.outerMethod();
    }
}
