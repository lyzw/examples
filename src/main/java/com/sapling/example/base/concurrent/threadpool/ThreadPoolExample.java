package com.sapling.example.base.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wei.zhou
 * @date 2019/4/7
 * @since 1.0
 */
public class ThreadPoolExample {

    public static void main(String[] args) {
        // 固定大小线程池,创建只有一个线程的线程池，且线程的存活时间是无限的；
        // 当该线程正繁忙时，对于新任务会进入阻塞队列中(无界的阻塞队列)
        ExecutorService fixThreadPool = Executors.newFixedThreadPool(10);
        // 单线程线程池-创建可容纳固定数量线程的池子，每隔线程的存活时间是无限的，当池子满了就不在添加线程了；
        // 如果池中的所有线程均在繁忙状态，对于新任务会进入阻塞队列中(无界的阻塞队列)
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        // 定时执行线程池-创建一个固定大小的线程池，线程池内线程存活时间无限制，
        // 线程池可以支持定时及周期性任务执行，如果所有线程均处于繁忙状态，
        // 对于新任务会进入DelayedWorkQueue队列中，这是一种按照超时时间排序的队列结构
        ExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(10);
        // 缓存线程池-当有新任务到来，则插入到SynchronousQueue中，
        // 由于SynchronousQueue是同步队列，因此会在池中寻找可用线程来执行，
        // 若有可以线程则执行，若没有可用线程则创建一个线程来执行该任务；
        // 若池中线程空闲时间超过指定大小，则该线程会被销毁。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 获取当前可用的线程数量进行创建作为并行级别,使用ForkJoinPool
        Executors.newWorkStealingPool();
    }
}
