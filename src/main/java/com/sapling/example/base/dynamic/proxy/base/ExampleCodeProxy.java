package com.sapling.example.base.dynamic.proxy.base;

import com.sapling.example.base.dynamic.proxy.ExampleServiceImpl;
import com.sapling.example.base.dynamic.proxy.IExampleService;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
public class ExampleCodeProxy implements IExampleService {
    IExampleService target;

    public ExampleCodeProxy(IExampleService target){
        this.target = target;
    }
    @Override
    public String printMsg(String abc) {
        System.out.println("ExampleCodeProxy before printMsg.");
        String value = target.printMsg(abc);
        System.out.println("ExampleCodeProxy before printMsg.");
        return value;
    }

    @Override
    public String method2(String method2) {
        System.out.println("ExampleCodeProxy before method2.");
        String value = target.printMsg(method2);
        System.out.println("ExampleCodeProxy before method2.");
        return value;
    }

    public static void main(String[] args) {
        IExampleService exampleService = new  ExampleServiceImpl();
        ExampleCodeProxy proxy = new ExampleCodeProxy(exampleService);
        proxy.printMsg("abc");
    }
}
