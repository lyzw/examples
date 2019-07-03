package com.sapling.springcloud.example.server.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
@RestController
@RequestMapping("example")
public class ExampleController {

    @Value("${server.port}")
    Integer port;

    @RequestMapping("test1")
    public String test1(String name) throws InterruptedException {
//        Thread.sleep(20000);
        return port.toString();
    }


    @RequestMapping("test2")
    public String test2(String name) {
        return port.toString();
    }
}
