package com.sapling.example.base.concurrency.helper;

import java.util.concurrent.*;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/11/25
 * @since 1.0
 */
public class CustomThreadFactory implements ThreadFactory {


    public static BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue(100);
    public static ThreadFactory threadFactory =new CustomThreadFactory();
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,1000L, TimeUnit.SECONDS,blockingQueue,threadFactory);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        return thread;
    }
}
