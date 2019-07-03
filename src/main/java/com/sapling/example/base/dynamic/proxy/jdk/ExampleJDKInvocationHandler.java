package com.sapling.example.base.dynamic.proxy.jdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sapling.example.base.dynamic.proxy.ExampleServiceImpl;
import com.sapling.example.base.dynamic.proxy.IExampleService;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
public class ExampleJDKInvocationHandler implements InvocationHandler {

    // 被代理对象，Object类型
    private Object target;

    public ExampleJDKInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ExampleJDKInvocationHandler : before invoke");
        Object result = method.invoke(target, args);
        System.out.println("ExampleJDKInvocationHandler : after invoke");
        return result;
    }

    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //生成的代理类Class文件保存在本地磁盘上
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IExampleService service = new ExampleServiceImpl();
        ExampleJDKInvocationHandler handler = new ExampleJDKInvocationHandler(service);
        IExampleService proxyObject = (IExampleService)handler.getProxy();
        String c = proxyObject.printMsg("abc");
        System.out.println(c);
        System.out.println(new File("$Proxy0" + ".class").getAbsolutePath());

    }
}
