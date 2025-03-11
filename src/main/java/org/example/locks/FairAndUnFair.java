package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnFair {

    //private static Lock unFairLock = new ReentrantLock();

    // follows fifo order and all threads gets chance to execute
    private static Lock fairLock = new ReentrantLock(true);

    public void accessResource(){
        fairLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" has accquired lock");
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            System.out.println(Thread.currentThread().getName()+" has released   lock");
            fairLock.unlock();
        }
    }

    public static void main(String[] args) {
        FairAndUnFair fairAndUnFair = new FairAndUnFair();

        Runnable task = () -> {
            fairAndUnFair.accessResource();
        };

        Thread t1 = new Thread(task , "Thread 1");
        Thread t2 = new Thread(task , "Thread 2");
        Thread t3 = new Thread(task , "Thread 3");
        t1.start();
        t2.start();
        t3.start();

        //synchronization issues
        /*
        * no fairness
        * indefinite blocking
        * no interruptibility
        * no read / write lock
        * because we don't have any control in using synchronized keyword*/
    }
}
