package org.example.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureINSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //callable
        Future<Integer> submit = executorService.submit(() -> 42);

        //runnable
        Future<?> submit2 = executorService.submit(() -> System.out.println("HEllo"));

        //runnable and return given parameter in method, upon completion
        Future<?> submit3 = executorService.submit(() -> System.out.println("HELLO") , "success" );

        //does not wait to complete
        if(submit.isDone()){
            System.out.println("Task is done above");
        }

        /*Returns true if this task completed.
         Completion may be due to normal termination, an exception, or cancellation
         -- in all of these cases, this method will return true.
        */

        // runnable does not return anything it is void
        // when we return something in executorService.submit(), instead of using runnable it uses callable interface
        // in case runnable not return anything but gives status of task execution which is given in future


        //wait for task to get completed, block main thread
        System.out.println(submit.get());

        System.out.println(submit3.get());

        if(submit.isDone()){
            System.out.println("Task is done below");
        }

        executorService.shutdown();
    }
}
