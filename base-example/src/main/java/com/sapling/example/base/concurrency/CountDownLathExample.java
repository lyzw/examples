package com.sapling.example.base.concurrency;

import com.sapling.example.base.concurrency.helper.CustomThreadFactory;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 同时启动示例
 *
 * @author wei.zhou
 * @date 2019/11/22
 * @since 1.0
 */
public class CountDownLathExample {

    public void countDownLatchTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        System.out.println("------------before latch  ----------- -> " + latch.getCount());

        for (int i =0 ;i <10;i++){
            CustomThreadFactory.threadPoolExecutor.execute(new CountDownLatchThread(latch));
        }
        latch.await();
        System.out.println("------------after latch await----------- -> " + latch.getCount());
        System.out.println("------------do something----------- -> " + latch.getCount());
        CustomThreadFactory.threadPoolExecutor.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLathExample example = new CountDownLathExample();
        example.countDownLatchTest();
    }


    static class CountDownLatchThread implements Runnable {
        CountDownLatch latch ;

        CountDownLatchThread(CountDownLatch latch) throws InterruptedException {
            this.latch = latch;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName() ;
            try {
                System.out.println(threadName + " -> ------------before sleep-----------");
                Thread.sleep(new Random().nextInt(10000));
                System.out.println(threadName + " -> latch.getCount() -> " + latch.getCount());
                System.out.println(threadName + " -> ------------after sleep-----------");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
