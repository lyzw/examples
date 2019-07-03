package com.sapling.example.base.hook;

/**
 * shut down hook example
 * 执行在JVM关闭之前的一些清理动作，如关闭资源，删除数据等
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/3/27
 * @since v1.0
 */
public class ShutdownHookExample extends Thread {


    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println("child thread exiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        System.out.println(1);
        Runtime.getRuntime().addShutdownHook(new ShutdownHookExample());
        System.out.println(2);
    }

    @Override
    public void run() {
        System.out.println("-------ShutdownHookExample begin----------");
        System.out.println("-------ShutdownHookExample end----------");
    }
}
