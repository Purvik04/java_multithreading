package org.example.countDownLatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        int noOfServices = 4;
        CountDownLatch latch = new CountDownLatch(noOfServices);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new DependentSerice(latch));
        executorService.submit(new DependentSerice(latch));
        executorService.submit(new DependentSerice(latch));
        executorService.submit(new DependentSerice(latch));
        executorService.submit(new DependentSerice(latch));

        //continue main thread when all services are completed and return 0
        // even we can use it with mannual thread pool also
//        for(int i = 0 ; i < noOfServices; i++){
//            new Thread(()->{
//                System.out.println("Hello");
//                try {
//                    Thread.sleep(6000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//                finally {
//                    latch.countDown();
//                }
//            }).start();
//        }
        latch.await();

        System.out.println("Main");
        executorService.shutdown();
    }
}

class DependentSerice implements Callable<String> {

    public final CountDownLatch latch;

    public DependentSerice(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws InterruptedException {

        try {
            System.out.println(Thread.currentThread().getName() + " service is running");
            Thread.sleep(2000);
        }
        finally {
            latch.countDown();
        }

        return "Ok";
    }
}
