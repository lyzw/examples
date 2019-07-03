package com.sapling.example.base.concurrent.sync.inheritablethreadlocal;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/15
 * @since v1.0
 */
public class InheritableThreadLocalExample implements Runnable {

    public InheritableThreadLocalExample(String value) {
        InheritableThreadLocalDemo.newInstance().value.set(new Demo(value));
    }

    @Override
    public void run() {
        System.out.println(
                Thread.currentThread().getName() + ":" + InheritableThreadLocalDemo.newInstance().value.get());
        Thread thread = new Thread(new ChildThread(Thread.currentThread().getName()));
        thread.start();

        System.out.println(
                Thread.currentThread().getName() + " get value again:" + InheritableThreadLocalDemo.newInstance().value.get());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InheritableThreadLocalExample("tom"));
        Thread thread2 = new Thread(new InheritableThreadLocalExample("jack"));
        thread.start();
        thread2.start();
    }
}

class ChildThread implements Runnable {


    private String parentName;

    public ChildThread(String name) {
        this.parentName = name;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("before sleep 3 seconds:" + currentThreadName + "-parentName[" + parentName + "]-value:" +
                InheritableThreadLocalDemo.newInstance().value.get());

        Thread t = new Thread(new GrandSonThread(currentThreadName));
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改示例代码，看看子线程中修改数据会影响其他线程的数据吗？明显是会的。。。
//        System.out.println(currentThreadName + " 修改数据为" + InheritableThreadLocalDemo.newInstance().value.get().name+"-after");
//        InheritableThreadLocalDemo.newInstance().value.get().setName(InheritableThreadLocalDemo.newInstance().value.get().name+"-after") ;
//        System.out.println(currentThreadName + " 修改数据后的数据为" + InheritableThreadLocalDemo.newInstance().value.get());
        System.out.println("after sleep 3 seconds:" + currentThreadName + "-parentName[" + parentName + "]-value:" +
                InheritableThreadLocalDemo.newInstance().value.get());

        System.out.println("child thread exit");
    }
}

class GrandSonThread implements Runnable {

    String parentName;

    public GrandSonThread(String name){
        this.parentName = name;
    }

    @Override
    public void run() {
        System.out.println("before sleep 6 seconds:" + Thread.currentThread().getName() + ":parentName[" + parentName + "]-value:" +
                InheritableThreadLocalDemo.newInstance().value.get());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after sleep 6 seconds:" + Thread.currentThread().getName() + ":parentName[" + parentName + "]-value:" +
                InheritableThreadLocalDemo.newInstance().value.get());
        System.out.println("grandson  thread exit");

    }
}


class InheritableThreadLocalDemo{

    private static InheritableThreadLocalDemo instance = new InheritableThreadLocalDemo();

    InheritableThreadLocal<Demo> value = new InheritableThreadLocal<>();

    private InheritableThreadLocalDemo() {

    }

    public static InheritableThreadLocalDemo newInstance() {
        return instance;
    }
}

class Demo{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Demo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                '}';
    }
}

