package com.sapling.example.base.concurrency;

import com.sapling.example.base.concurrency.helper.CustomThreadFactory;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/11/22
 * @since 1.0
 */
public class CyclicBarrierExample {


    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            CustomThreadFactory.threadPoolExecutor.execute(new CyclicBarrierThread(cyclicBarrier));
        }
//        cyclicBarrier.wait();
//        while (!cyclicBarrier) {
//            Thread.sleep(1000L);
//        }
    }


    static class CyclicBarrierThread implements Runnable {
        CyclicBarrier cyclicBarrier;

        public CyclicBarrierThread(CyclicBarrier cyclicBarrier) {
            if (cyclicBarrier == null) {
                throw new IllegalArgumentException("cyclicBarrier is required!");
            }
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println(threadName + " -> ------------before sleep-----------");
                Thread.sleep(new Random().nextInt(10000));
                System.out.println(threadName + " -> ------------after sleep-----------");
                System.out.println(threadName + " -> before cyclicBarrier.await()...");
                System.out.println(threadName + " -> cyclicBarrier.getNumberWaiting() -> " + cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
                System.out.println(threadName + " -> after cyclicBarrier.await()...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
