package com.sapling.example.base.collection.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueExample {

    public static void testDrainTo(){
        BlockingQueue queue = new ArrayBlockingQueue(10);
        queue.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        List list2 = new ArrayList<Integer>();
        queue.drainTo(list2);
        System.out.println("queue.size -> " + queue.size());
        System.out.println("list2 -> " + list2);

    }

    public static void testDrainToWithSize(){
        BlockingQueue queue = new ArrayBlockingQueue(10);
        queue.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        List list2 = new ArrayList<Integer>();
        queue.drainTo(list2,20);
        System.out.println("queue.size -> " + queue.size());
        System.out.println("list2 -> " + list2);

    }



    public static void main(String[] args) {
        testDrainTo();
        testDrainToWithSize();
    }

}
