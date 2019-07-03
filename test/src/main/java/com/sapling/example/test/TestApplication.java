package com.sapling.example.test;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s.com.eoi.filters.SGrok$;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/16
 * @since v1.0
 */
@SpringBootApplication
@RestController
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);

    }

    @RequestMapping("test")
    public Map test(){
        return SGrok$.MODULE$.patterns();
    }
}
