package org.example;

public class InterruptDemo extends Thread {

    @Override
    public void run() {
       try {
          for (int i = 0; i < 5; i++) {
              System.out.println(Thread.currentThread().getName());
              Thread.yield(); // hint to the scheduler that this thread is willing to give up its timeslice
          }
       } catch (Exception e) {
           System.out.println(e);
       }

    }

//    @Override
//    public void run() {
//       try {
//           Thread.sleep(1000);
//           System.out.println("Hello");
//       } catch (InterruptedException e) {
//           Thread.currentThread().interrupt();
//           System.out.println(e.getMessage()  + " thread is interrupted");
//       }
//
//    }


    public static void main(String[] args) throws InterruptedException {
//        InterruptDemo interruptDemo = new InterruptDemo();
//        interruptDemo.start();
//        interruptDemo.interrupt();
//        Thread.sleep(1000);
//        System.out.println(interruptDemo.isInterrupted());

        InterruptDemo t1 = new InterruptDemo();
        InterruptDemo t2 = new InterruptDemo();
        t1.start();
        t2.start();

    }
}
