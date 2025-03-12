package org.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executorService.submit(()-> System.out.println("Fact of " + finalI + " is "+ factorial(finalI)));
        }

        /*
        Initiates an orderly shutdown in which previously submitted tasks are executed,
        but no new tasks will be accepted.

        immediately return to main thread, no wait for task to complete
        */
        executorService.shutdown();

        /*
        * Attempts to stop all actively executing tasks, halts the processing of waiting tasks,
        * and returns a list of the tasks that were awaiting execution.
        * */
//        System.out.println(executorService.shutdownNow());

        try {
            /*
            Returns true if the ExecutorService terminated before the timeout,
            or false if the timeout elapsed before termination.
            Just Block Main thread until timeout , it doesn't stop task from being executed
             */
            //max wait 3 seconds or all tasks gets completed
            while(!executorService.awaitTermination(2, TimeUnit.SECONDS)){
                System.out.println("waiting");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Total time " + (System.currentTimeMillis() - startTime));

    }

    public static long factorial(int n) {
        try {
            //suppose some big task is being done
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long res = 1;

        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }
}


