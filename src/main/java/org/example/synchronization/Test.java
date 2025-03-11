package org.example.synchronization;

public class Test {
    public static void main(String[] args) {
        Counter counter = new Counter();
        MyThread m1 = new MyThread(counter);
        MyThread m2 = new MyThread(counter);

        m1.start();
        m2.start();

        try {
            m1.join();
            m2.join();
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(counter.getCount());
    }
}
