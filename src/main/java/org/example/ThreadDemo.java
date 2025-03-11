package org.example;

public class ThreadDemo extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println("Thread is running");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        System.out.println(threadDemo.getState());
        threadDemo.start();
        System.out.println(Thread.currentThread().getName());
        System.out.println(threadDemo.getState());
        Thread.sleep(100);
        System.out.println(threadDemo.getState());
        System.out.println(threadDemo.getState());


    }

}

