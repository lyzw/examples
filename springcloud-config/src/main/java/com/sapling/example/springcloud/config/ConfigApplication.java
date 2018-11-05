package com.sapling.example.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/30
 * @since v1.0
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
@RestController
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

    @GetMapping("info")
    public String info() {
        return "springcloud config server~";
    }
}
