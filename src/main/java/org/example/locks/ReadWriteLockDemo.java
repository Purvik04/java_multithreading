package org.example.locks;

import org.example.synchronization.Counter;

import java.util.concurrent.locks.*;

public class ReadWriteLockDemo {
    // no unnecessary locking due to read write lock
    private static int count = 0;

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static final Lock readLock = lock.readLock();

    private static final Lock writeLock = lock.writeLock();

    public static void increment(){
        writeLock.lock();
        try{
            count++;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e);
        } finally {
            writeLock.unlock();
        }
    }

    public static int getCount(){
        //multiple readers can accquire this lock simultaneously
        readLock.lock();
        try{
            return count;
        }
        finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {

        Runnable readTask = () -> {
            for(int i = 0 ; i< 10; i++){
                System.out.println(Thread.currentThread().getName() + " is reading " + getCount());
            }
        };

        Runnable writeTask = () -> {
            for(int i = 0 ; i< 10; i++){
                increment();
                System.out.println(Thread.currentThread().getName() + " is writing" );
            }
        };

        Thread writerThread = new Thread(writeTask, "Writer Thread");
        Thread readerThread1 = new Thread(readTask, "Reader Thread1");
        Thread readerThread2 = new Thread(readTask, "Reader Thread2");

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        try{
            writerThread.join();
            readerThread1.join();
            readerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e);
        }

        System.out.println("Final Count " + getCount());
    }
}
