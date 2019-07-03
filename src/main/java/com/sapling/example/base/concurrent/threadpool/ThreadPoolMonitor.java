package com.sapling.example.base.concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.*;

import com.sapling.example.base.concurrent.thread.thread.ThreadDemo;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/1/15
 * @since v1.0
 */
public class ThreadPoolMonitor {
    static int size = 100;
    static BlockingDeque worker = new LinkedBlockingDeque(size);
    static ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 50, 0, TimeUnit.SECONDS, worker, new CustomerThreadFactory());

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            for (int index = 0; index < size; index++) {
                executorService.execute(() -> {
                    try {
                        Thread.sleep(new Random().nextInt(10)*2000);
//                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();
        Thread.sleep(100);
        while (executorService.getCompletedTaskCount()<size){
            System.out.println("executorService.getActiveCount()->" + executorService.getActiveCount());
            System.out.println("executorService.getCompletedTaskCount()->" + executorService.getCompletedTaskCount());
            System.out.println("executorService.getTaskCount()->" + executorService.getTaskCount());
            System.out.println("executorService.getMaximumPoolSize()->" + executorService.getMaximumPoolSize());
            System.out.println("executorService.getLargestPoolSize()->" + executorService.getLargestPoolSize());
            System.out.println("executorService.getPoolSize()->" + executorService.getPoolSize());
            Thread.sleep(3000);
        }

    }
}
