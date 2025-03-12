package org.example.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(()-> System.out.println("Hello World 1"), 2, 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(()-> System.out.println("Hello World 2"), 2, 5, TimeUnit.SECONDS);

//        scheduledExecutorService.schedule(()-> {
//            System.out.println("Hello World");
//            scheduledExecutorService.shutdown();
//        }, 20, TimeUnit.SECONDS);
        //same as above
        Thread.sleep(10000);
        scheduledExecutorService.shutdown();

//        scheduledExecutorService.schedule(() -> {
//            System.out.println("Hello World");
//        }, 5, TimeUnit.SECONDS);


        //when variable load but short lived tasks
        // dynamically adjust thread pool size as per load
        Executors.newCachedThreadPool();
    }
}
