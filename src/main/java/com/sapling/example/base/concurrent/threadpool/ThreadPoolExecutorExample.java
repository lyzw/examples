package com.sapling.example.base.concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExample {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }



    public static void main(String[] args) throws InterruptedException {
        System.out.println(new ThreadPoolExecutorExample().ctl.get());
        System.out.println(workerCountOf(new ThreadPoolExecutorExample().ctl.get()));
        System.out.println(CAPACITY);
        Thread.sleep(10000);
        LinkedBlockingQueue workQueue = new LinkedBlockingQueue(10000);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.MINUTES, workQueue);
        for (int index = 0;index<100000;index ++) {
            Thread.sleep(100);
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(new Random().nextInt(3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        while (poolExecutor.getCompletedTaskCount() < 100){
            System.out.println("getActiveCount -> " + poolExecutor.getActiveCount());
            System.out.println("getQueue().size() -> " + poolExecutor.getQueue().size());
            System.out.println("getCompletedTaskCount -> " + poolExecutor.getCompletedTaskCount());
            Thread.sleep(500);
        }
        poolExecutor.shutdown();
    }
}
