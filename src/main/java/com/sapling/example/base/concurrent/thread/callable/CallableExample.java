package com.sapling.example.base.concurrent.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/2/25
 * @since v1.0
 */
public class CallableExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask futureTask = new FutureTask(()-> {
            return "abc";
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        while (!futureTask.isDone()){
            Thread.sleep(1000);
        }
        System.out.println(futureTask.get());
    }
}
