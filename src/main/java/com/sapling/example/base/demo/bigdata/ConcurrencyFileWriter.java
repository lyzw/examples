package com.sapling.example.base.demo.bigdata;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrencyFileWriter {

    private BlockingQueue<byte[]> queue;

    private Integer queueSize;

    private Integer workerSize;

    private Integer writeBufferSize;

    private File file;

    private List<Worker> workers = new CopyOnWriteArrayList<>();

    private volatile AtomicLong count = new AtomicLong(0);


    public ConcurrencyFileWriter(Integer queueSize, Integer workerSize, Integer writeBufferSize, File file) {
        queue = new ArrayBlockingQueue<byte[]>(queueSize);
        this.queueSize = queueSize;
        this.workerSize = workerSize;
        this.writeBufferSize = writeBufferSize;
        this.file = file;
    }

    public void start() {
        ExecutorService service = Executors.newFixedThreadPool(this.workerSize);
        service.execute(new Worker(this));
    }

    public void stop() {
        this.workers.stream().forEach(f -> f.stopFlag = false);
    }

    public Long fileSize() {
        return count.get();
    }

    public void addData(byte[] bytes) {
        queue.add(bytes);
    }

    private static class Worker implements Runnable {
        ConcurrencyFileWriter fileWriter;
        volatile boolean stopFlag = true;

        private Integer writeBufferSize;

        public Worker(ConcurrencyFileWriter fileWriter) {
            this.fileWriter = fileWriter;
            this.writeBufferSize = fileWriter.writeBufferSize;
        }

        @Override
        public void run() {
            fileWriter.workers.add(this);

            stopFlag = true;
            while (stopFlag) {
                List<byte[]> contents = new ArrayList<>();
                fileWriter.queue.drainTo(contents);
                if (contents == null || contents.size() == 0) {
                    try {
                        Thread.sleep(10);
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                FileLock fileLock = null;
                MappedByteBuffer mbb = null;
                FileChannel channel = null;
                FileOutputStream fos = null;
                try  {
                    long from = fileWriter.count.get();
                    int size = contents.stream().mapToInt(f -> f.length).sum();
                    fos = new FileOutputStream(fileWriter.file);
                    channel = fos.getChannel();
                    mbb = channel.map(FileChannel.MapMode.READ_WRITE, from, size);

                    synchronized (this.getClass()) {
                        fileLock = channel.lock(from, size, true);
                        fileWriter.count.compareAndSet(from, from + size);
                        while (fileLock == null || !fileLock.isValid()) {
                            fileLock = fileLock = channel.lock(from, size, true);
                        }
                    }
                    byte[] bytes = new byte[size];
                    mbb.put(bytes);
                    mbb.force();
                    fos.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
//                    try {
//                        fileLock.release();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    forceClose(mbb);

                }
            }

        }

        /**
         * 强制关闭MappedByteBuffer
         * @param mbb
         */
        private static void forceClose(MappedByteBuffer mbb) {
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    try {
                        Method getCleanerMethod = mbb.getClass().getMethod("cleaner", new Class[0]);
                        getCleanerMethod.setAccessible(true);
                        sun.misc.Cleaner cleaner = (sun.misc.Cleaner)
                                getCleanerMethod.invoke(mbb, new Object[0]);
                        cleaner.clean();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }
//        public FileLock lockAccessFileLocation(RandomAccessFile file, Integer contentSize, Long from, boolean shared) throws IOException {
//
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrencyFileWriter writer = new ConcurrencyFileWriter(1000, 1, 10, new File("D:\\test1.txt"));
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.addData("是打发斯蒂芬\r\n".getBytes());
        writer.start();
        while (writer.queue.size() != 0) {
            System.out.println("size -> " + writer.count);
            Thread.sleep(1000);
        }
        Thread.sleep(10000);
        writer.stop();
    }

}
