package org.example.synchronization;

public class Counter {
    private int count = 0;

    //whole method sync but if you dont want to make whole method sync
//    public synchronized void increment() {
//        count++;
//    }
    public  void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount(){
        return count;
    }
}
