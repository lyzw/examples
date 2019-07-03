package com.sapling.example.base.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/1/15
 * @since v1.0
 */
public class CustomerThreadFactory implements ThreadFactory {

    protected final AtomicInteger threadId = new AtomicInteger(1);

    /**
     * return new thread with name (runnable's class + "-" + customer increment thread id + "-" + system thread id )
     *
     * @param runnable
     * @return
     */
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        String prefix =
                runnable.getClass().getName() + "-" + threadId.getAndIncrement();
        thread.setName(prefix + "-" + thread.getId());
        return thread;
    }
}
