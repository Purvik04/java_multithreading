package org.example.threadpool;

public class ManualThreadPool {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
//        for (int i = 1; i <= 10; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + " " + finalI + " " + factorial(finalI));
//            }).start();
//        }
//        //not getting coreect time beacue not waiting for all threads
//        System.out.println("Total time" + (System.currentTimeMillis() - startTime));


        /*
        * in manual management disadvantage
        * no thread objects are reused
        * we have to manage by own
         */


        Thread[] threads = new Thread[10];

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            threads[i-1] = new Thread(()-> System.out.println("Fact of "+ finalI +" is " +factorial(finalI)));
            threads[i-1].start();
        }

        for (Thread t : threads){
            try{
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Total time " + (System.currentTimeMillis() - startTime));

    }

    public static long factorial(int n) {

        try {
            //suppose some big task is beign done
            Thread.sleep(1000);
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
