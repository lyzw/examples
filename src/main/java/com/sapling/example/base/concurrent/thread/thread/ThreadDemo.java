package com.sapling.example.base.concurrent.thread.thread;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/1/15
 * @since v1.0
 */
public class ThreadDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(this.getClass().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
