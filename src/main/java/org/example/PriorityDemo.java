package org.example;

public class PriorityDemo extends Thread {

    public PriorityDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String a = "";
            for (int j = 0; j < 100000; j++) {
                a += "a";
            }
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        PriorityDemo l = new PriorityDemo("Low");
        PriorityDemo m = new PriorityDemo("Medium");
        PriorityDemo h = new PriorityDemo("High");
        l.setPriority(Thread.MIN_PRIORITY);
        m.setPriority(Thread.NORM_PRIORITY);
        h.setPriority(Thread.MAX_PRIORITY);
        l.start();
        m.start();
        h.start();
    }
}
