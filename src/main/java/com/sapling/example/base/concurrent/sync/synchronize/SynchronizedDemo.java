package com.sapling.example.base.concurrent.sync.synchronize;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.sapling.common.tools.common.ReflectUtil;


/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/25
 * @since v1.0
 */
public class SynchronizedDemo {

    static Object syncStaticObject = new Object();
    private Object syncObject = new Object();

    public static synchronized void testStaticSynchronizedFunc() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " -> testStaticSynchronizedFunc " + new Date());
    }

    /**
     * 同步实例代码块
     */
    public void testSynchronizedBean() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " -> testSynchronizedBean " + new Date());
        }
    }

    /**
     * 同步对象代码块
     */
    public void testSynchronizedClass() throws InterruptedException {
        synchronized (this.getClass()) {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " -> testSynchronizedClass " + new Date());
        }
    }

    /**
     * 同步代码方法
     */
    public synchronized void testSynchronizedFunc() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " -> testSynchronizedFunc " + new Date());
    }

    /**
     * 同步任意对象代码块
     */
    public void testSynchronizedStaticObject() throws InterruptedException {
        synchronized (syncStaticObject) {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " -> testSynchronizedStaticObject " + new Date());

        }
    }

    /**
     * 同步任意对象代码块
     */
    public void testSynchronizedObject() throws InterruptedException {
        synchronized (this.syncObject) {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " -> testSynchronizedObject " + new Date());

        }
    }

    public void  testSynchronizedFuncObject() throws InterruptedException {
        String sync = new String();
        synchronized (sync) {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " -> testSynchronizedFuncObject " + new Date());

        }
    }

    static class RunableExample implements Runnable{

        Object target;

        Class clazz;

        String methodName;

        public RunableExample(Object target, Class clazz, String methodName) {
            this.target = target;
            this.clazz = clazz;
            this.methodName = methodName;
        }

        @Override
        public void run() {
            for (int index = 0; index < 3; index++) {
                try {
                    ReflectUtil. invokeMethod(target,clazz,methodName,null,null);
                }  catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo = new SynchronizedDemo();

        System.out.println("#############   testStaticSynchronizedFunc   ###################");
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testStaticSynchronizedFunc"),"testStaticSynchronizedFunc-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testStaticSynchronizedFunc"),"testStaticSynchronizedFunc-2").start();
        Thread.sleep(30000);

        System.out.println("#############   testSynchronizedBean   ###################");

        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedBean"),"testSynchronizedBean-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedBean"),"testSynchronizedBean-2").start();
        Thread.sleep(30000);
        System.out.println("#############   testSynchronizedClass   ###################");

        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedClass"),"testSynchronizedClass-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedClass"),"testSynchronizedClass-2").start();
        Thread.sleep(30000);
        System.out.println("#############   testSynchronizedFunc   ###################");

        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedFunc"),"testSynchronizedFunc-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedFunc"),"testSynchronizedFunc-2").start();
        Thread.sleep(30000);
        System.out.println("#############   testSynchronizedStaticObject   ###################");

        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedStaticObject"),"testSynchronizedStaticObject-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedStaticObject"),"testSynchronizedStaticObject-2").start();
        Thread.sleep(30000);
        System.out.println("#############   testSynchronizedObject   ###################");

        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedObject"),"testSynchronizedObject-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedObject"),"testSynchronizedObject-2").start();
        Thread.sleep(30000);
        System.out.println("#############   testSynchronizedFuncObject   ###################");

        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedFuncObject"),"testSynchronizedFuncObject-1").start();
        new Thread(new RunableExample(demo,SynchronizedDemo.class,"testSynchronizedFuncObject"),"testSynchronizedFuncObject-2").start();
        Thread.sleep(30000);

    }
}
