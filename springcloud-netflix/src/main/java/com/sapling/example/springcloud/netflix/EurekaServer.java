package com.sapling.example.springcloud.netflix;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/28
 * @since v1.0
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServer.class).web(WebApplicationType.SERVLET).run(args);
    }
}
