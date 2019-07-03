package com.sapling.example.base.dynamic.proxy.cglib;

import java.lang.reflect.Method;

import com.sapling.example.base.dynamic.proxy.ExampleServiceImpl;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGlib 代理样例
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
public class ExampleCGLibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before Method Invoke");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After Method Invoke");
        return result;
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/weizhou/workspace/examples/cglib/");

        ExampleCGLibProxy proxy = new ExampleCGLibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ExampleServiceImpl.class);
        enhancer.setCallback(proxy);

        ExampleServiceImpl dao = (ExampleServiceImpl)enhancer.create();
        dao.printMsg("abc");
    }
}
