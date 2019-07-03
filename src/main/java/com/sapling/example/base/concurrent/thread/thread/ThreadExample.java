package com.sapling.example.base.concurrent.thread.thread;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/27
 * @since v1.0
 */
public class ThreadExample  {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
    }

}

