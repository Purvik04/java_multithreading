package org.example;

public class DaemonThread  extends  Thread{

    @Override
    public void run() {
        while (true){
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) {

        DaemonThread d1 = new DaemonThread();
        d1.setDaemon(true);
        // when we set the daemon thread to true it will run in the background
        // jvm will not wait for the daemon thread to complete
        d1.start();

        DaemonThread d2 = new DaemonThread();
        d2.start();
        //jvm will wait for this thread to complete
        System.out.println("Main Done");
    }

}
