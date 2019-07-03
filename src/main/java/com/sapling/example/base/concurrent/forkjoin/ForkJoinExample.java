package com.sapling.example.base.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wei.zhou
 * @date 2019/4/7
 * @since 1.0
 */
public class ForkJoinExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        BatchInsertTask batchInsertTask = new BatchInsertTask(list);
        long t1 = System.currentTimeMillis();
        ForkJoinTask<Integer> reslut = forkJoinPool.submit(batchInsertTask);
        System.out.println(reslut.get());
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

    }

    static class BatchInsertTask extends RecursiveTask<Integer> {
        //要插入的数据
        List<Integer> records;
        public BatchInsertTask(List<Integer> records) {
            this.records = records;
        }

        @Override
        protected Integer compute() {
            //当要插入的数据少于3，则直接插入
            if (records.size() < 3) {
                return computeDirectly();
            } else {
                //如果要插入的数据大于等于3，则进行分组插入
                int size = records.size();

                //第一个分组
                BatchInsertTask aTask = new BatchInsertTask(records.subList(0, size / 2));
                //第二个分组
                BatchInsertTask bTask = new BatchInsertTask(records.subList(size / 2, records.size()));
                //两个任务并发执行起来
                invokeAll(aTask, bTask);
                //两个分组的插入的行数加起来
                return aTask.join() + bTask.join();
            }

        }
        /**
         * 真正插入数据的逻辑
         */
        private int computeDirectly() {
            try {
                Thread.sleep((long) (records.size() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("插入了：" + Arrays.toString(records.toArray()));
            return records.size();
        }
    }
}
