package com.sapling.example.base.dynamic.proxy;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
public class ExampleServiceImpl implements IExampleService {
    @Override
    public String printMsg(String abc) {
        System.out.println(abc);
        return abc + System.currentTimeMillis();
    }

    @Override
    public String method2(String method2) {
        System.out.println("method2:" + method2);
        return method2;
    }
}
