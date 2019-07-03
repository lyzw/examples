package com.sapling.example.springcloud.config.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wei.zhou
 * @date 2019/4/10
 * @since 1.0
 */
public class BeanCycleExample {

    private static String path = "bean.xml";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        context.getBean("helloBean");
    }
}
