package com.sapling.example.base.lambda;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/26
 * @since v1.0
 */
public interface FunctionalInterfaceDemo {

    void test2();

    default void test(){
        System.out.println("this is a test function!");
    }
}




