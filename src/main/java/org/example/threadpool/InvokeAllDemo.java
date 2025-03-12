package org.example.threadpool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllDemo {
    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> c1 = () ->{
            Thread.sleep(500);
            System.out.println("Task1");
            return 1;
        };

        Callable<Integer>  c2 = () ->{
            Thread.sleep(500);
            System.out.println("Task2");
            return 2;
        };

        Callable<Integer>  c3 = () ->{
            Thread.sleep(1000);
            System.out.println("Task3");
            return 3;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // instead of submitting task one by one we can submit all at once
        // it blocks main thread
//        List<Future<Integer>> futures = executorService.invokeAll(Arrays.asList(c1, c2, c3));
        List<Future<Integer>> futures2 = null;
        try {
            // wait for 1 second to execute all threads
            // cancel tasks if not execution in 1 second
            futures2 = executorService.invokeAll(Arrays.asList(c1, c2, c3), 1, TimeUnit.SECONDS);

            //invokeAny method start all threads but return result of only one thread who executes first
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (Future<Integer> future : futures2) {
            try {
                System.out.println(future.get());
            }
            catch (CancellationException e) {
                System.out.println(e + " from future");
            }
            catch (InterruptedException e) {

            } catch (ExecutionException e) {
            }
        }

        executorService.shutdown();
        System.out.println("Hello world");


    }
}
