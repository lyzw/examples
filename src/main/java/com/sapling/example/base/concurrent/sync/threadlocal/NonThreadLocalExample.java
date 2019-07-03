package com.sapling.example.base.concurrent.sync.threadlocal;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/15
 * @since v1.0
 */
public class NonThreadLocalExample implements Runnable {

    private Integer value;

    public NonThreadLocalExample(Integer value) {
        this.value = value;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(
                "SimpleThread - [" + threadName + "] set value :" + value);
        NonThreadLocalDemo.newInstance().value = value;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SimpleThread [" + threadName + "] get value :" +
                NonThreadLocalDemo.newInstance().value);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new NonThreadLocalExample(10));
        Thread t2 = new Thread(new NonThreadLocalExample(100));
        t1.start();
        t2.start();
    }
}


class NonThreadLocalDemo {
    private static NonThreadLocalDemo instance = new NonThreadLocalDemo();

    Integer value;

    private NonThreadLocalDemo() {

    }

    public static NonThreadLocalDemo newInstance() {
        return instance;
    }
}
