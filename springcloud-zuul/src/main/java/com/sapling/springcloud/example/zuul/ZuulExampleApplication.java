package com.sapling.springcloud.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/24
 * @since v1.0
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulExampleApplication.class, args);
    }
}
