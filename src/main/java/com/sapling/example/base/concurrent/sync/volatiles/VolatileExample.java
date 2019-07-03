package com.sapling.example.base.concurrent.sync.volatiles;

import java.util.Date;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/26
 * @since v1.0
 */
public class VolatileExample {

    boolean flag = false;

    volatile boolean flag2 = false;

    public void loopByFlag(){
        while (!flag){
            System.out.println(new Date() + " -> loopByFlag");
        }
    }

    public void loopByFlag2(){
        while (!flag2){
            System.out.println(new Date() + " -> loopByFlag2");
        }
    }

    public void changeFlag(boolean flag){
        this.flag = flag;
        System.out.println("changeFlag -> " + flag);
    }

    public void changeFlag2(boolean flag){
        this.flag2 = flag;
        System.out.println("changeFlag2 -> " + flag);
    }


    public static void main(String[] args) throws InterruptedException {
        VolatileExample example = new VolatileExample();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                example.loopByFlag2();
            }
        });
        t.start();

        Thread.sleep(100);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.changeFlag2(true);
            }
        });
        t2.start();
    }
}
