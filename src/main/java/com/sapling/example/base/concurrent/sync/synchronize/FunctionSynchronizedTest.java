package com.sapling.example.base.concurrent.sync.synchronize;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/25
 * @since v1.0
 */
public class FunctionSynchronizedTest {

    static int value = 0;

    public synchronized String test() {
        value++;
        return String.valueOf(value);
    }

    public  String test2() {
        synchronized(this){
            value++;
        }

        return String.valueOf(value);
    }

}
