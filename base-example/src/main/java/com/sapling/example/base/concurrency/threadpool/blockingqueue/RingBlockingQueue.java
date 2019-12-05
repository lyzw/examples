package com.sapling.example.base.concurrency.threadpool.blockingqueue;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/11/26
 * @since 1.0
 */
public class RingBlockingQueue<T> extends AbstractQueue implements BlockingQueue {

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(Object object) throws InterruptedException {

    }

    @Override
    public boolean offer(Object object, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Object take() throws InterruptedException {
        return null;
    }

    @Override
    public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection c) {
        return 0;
    }

    @Override
    public int drainTo(Collection c, int maxElements) {
        return 0;
    }

    @Override
    public boolean offer(Object object) {
        return false;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
