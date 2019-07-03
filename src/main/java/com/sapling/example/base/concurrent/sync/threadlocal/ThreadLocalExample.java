package com.sapling.example.base.concurrent.sync.threadlocal;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/15
 * @since v1.0
 */
public class ThreadLocalExample implements Runnable {
    private Integer value;

    public ThreadLocalExample(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] set value :" + value);
        ThreadLocalDemo.newInstance().value.set(value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("get value [" + threadName + "]:" +
                ThreadLocalDemo.newInstance().value.get());
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadLocalExample(10));
        Thread t2 = new Thread(new ThreadLocalExample(100));
        t1.start();
        t2.start();
    }
}


class ThreadLocalDemo {

    private static ThreadLocalDemo instance = new ThreadLocalDemo();

    ThreadLocal<Integer> value = new ThreadLocal<>();

    private ThreadLocalDemo() {

    }

    public static ThreadLocalDemo newInstance() {
        return instance;
    }

}
