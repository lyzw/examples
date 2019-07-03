package com.sapling.example.base.concurrent.thread.future.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/29
 * @since v1.0
 */
public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Long> futureTask = new FutureTask<>(new CallableDemo());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(
                "cancel sub futureTask （mayInterruptIfRunning is true）:" +
                        futureTask.cancel(true));
        System.out.println("main thread value :" + System.currentTimeMillis());
        System.out.println(
                "check sub futureTask is canceled:" + futureTask.isCancelled());
        if (!futureTask.isCancelled()) {
            System.out.println("sub futureTask value  :" + futureTask.get());
        }
        System.out.println("============== futureTask2 ======================");
        FutureTask<Long> futureTask2 = new FutureTask<>(new CallableDemo());
        Thread thread2 = new Thread(futureTask2);
        thread2.start();
        System.out.println(
                "cancel sub futureTask2 （mayInterruptIfRunning is false）:" +
                        futureTask2.cancel(false));
        System.out.println("main thread value :" + System.currentTimeMillis());
        System.out.println(
                "check sub futureTask2 is canceled:" + futureTask2.isCancelled());
        if (!futureTask2.isCancelled()) {
            System.out.println("sub futureTask2 value  :" + futureTask2.get());
        }
        System.out.println("============== futureTask3 ======================");
        FutureTask<Long> futureTask3 = new FutureTask<>(new CallableDemo());
        Thread thread3 = new Thread(futureTask3);
        thread3.start();
        //        System.out.println("cancel sub thread （mayInterruptIfRunning is false）:" + futureTask3.cancel(false));
        System.out.println("main thread value :" + System.currentTimeMillis());
        System.out.println(
                "check sub futureTask3 is canceled:" + futureTask3.isCancelled());
        if (!futureTask3.isCancelled()) {
            System.out.println("sub futureTask3 value  :" + futureTask3.get());
        }
    }

    static class CallableDemo implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            System.out.println("[" + Thread.currentThread().getName() +
                    "]Callable thread is running...");
            Thread.sleep(3000);
            Long value = System.currentTimeMillis();
            System.out.println("[" + Thread.currentThread().getName() +
                    "]Callable thread going to exit...");
            return value;
        }
    }
}


