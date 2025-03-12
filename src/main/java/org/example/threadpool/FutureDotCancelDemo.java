package org.example.threadpool;

import org.example.locks.ReentrantLockDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDotCancelDemo{

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Future<Integer> future = executorService.submit(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("Hello");
      return 42;
    });

    // this will cancel the task
    // if true then even if task is running , it will be stopped from execution
    // and mark isCancle as true in future
//    future.cancel(true);
//    System.out.println(future.isCancelled());
//    System.out.println(future.isDone());
//    executorService.shutdown();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    // isDone will always true whethre is completed normally or abnormally
    future.cancel(false);
    System.out.println(future.isCancelled());
    System.out.println(future.isDone());
    executorService.shutdown();


  }

}
