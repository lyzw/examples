package com.sapling.example.base.collection.queue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {


    public static void testRemoveObject(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(20);
        blockingQueue.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,1,2}));
        blockingQueue.remove(2);
        System.out.println(blockingQueue);

    }

    public static void main(String[] args) {
        testRemoveObject();
    }
}
