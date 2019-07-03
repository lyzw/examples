package com.sapling.example.base.concurrent.sync.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/26
 * @since v1.0
 */
public class LockExample {

    Integer value = 0;
    static int count = 100;
    Lock lock = new ReentrantLock();


    static CountDownLatch countDownLatch = new CountDownLatch(count);
    static CountDownLatch countDownLatch2 = new CountDownLatch(count);

    public void increaseValueWithLock() {
        lock.lock();
        value++;
        lock.unlock();
    }

    public void increaseValueWithoutLock() {
        value++;
    }

    public static void main(String[] args) {
        LockExample lockExample = new LockExample();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 100; index++) {
                    lockExample.increaseValueWithLock();
                }
                countDownLatch.countDown();
            }
        };
        for (int index = 0; index < count; index++) {
            Thread t1 = new Thread(runnable, "thread-" + index);
            t1.start();
        }
        while (countDownLatch.getCount() >0 ){

        }
        System.out.println("increaseValueWithLock->" + lockExample.value);


        lockExample.value = 0;
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 100; index++) {
                    lockExample.increaseValueWithoutLock();
                }
                countDownLatch2.countDown();
            }
        };
        for (int index = 0; index < count; index++) {
            Thread t1 = new Thread(runnable2, "thread-" + index);
            t1.start();
        }
        while (countDownLatch.getCount() >0 ){

        }
        System.out.println("increaseValueWithoutLock -> "+ lockExample.value);

    }
}
