package com.sapling.example.base.concurrency.threadpool;

import com.sapling.example.base.concurrency.helper.CustomThreadFactory;

import java.util.Random;
import java.util.concurrent.*;

/**
 * thread pool使用示例
 *
 * @author wei.zhou
 * @date 2019/12/2
 * @since 1.0
 */
public class ThreadPoolExample {

    private static BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
    private static ThreadFactory threadFactory =new CustomThreadFactory();

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,20,10L, TimeUnit.SECONDS,blockingQueue,threadFactory);

    public static void verifyWhenRaiseThread() throws InterruptedException {
        for (int index = 1; index < 100; index ++ ){
            threadPoolExecutor.execute(new ThreadPoolThreadDemo(10 * index));
            System.out.println("threadPoolExecutor.getActiveCount() -> " + threadPoolExecutor.getActiveCount());
            System.out.println("threadPoolExecutor.getQueue().size() -> " + threadPoolExecutor.getQueue().size());
            Thread.sleep(20);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        verifyWhenRaiseThread();
    }

    static class ThreadPoolThreadDemo implements Runnable {

        private Integer sleepTime;

        public ThreadPoolThreadDemo(Integer sleepTime) {
            this.sleepTime = new Random().nextInt(sleepTime * 10);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " -> sleep [" + sleepTime + "]");
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " -> awake !");
        }
    }


}
