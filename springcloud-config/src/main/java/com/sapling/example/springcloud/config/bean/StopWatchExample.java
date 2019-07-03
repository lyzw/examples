package com.sapling.example.springcloud.config.bean;

import org.springframework.util.StopWatch;

import java.util.Random;

/**
 * @author wei.zhou
 * @date 2019/4/9
 * @since 1.0
 */
public class StopWatchExample {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("StopWatchExample");
        stopWatch.start("currentTaskName");
        System.out.println(stopWatch.currentTaskName());
        Thread.sleep(new Random().nextInt(1000));
        stopWatch.stop();

        stopWatch.start("getId");
        System.out.println(stopWatch.getId());
        Thread.sleep(new Random().nextInt(1000));
        stopWatch.stop();

        stopWatch.start("getTaskCount");
        System.out.println(stopWatch.getTaskCount());
        Thread.sleep(new Random().nextInt(1000));
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
