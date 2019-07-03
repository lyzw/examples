package com.sapling.springcloud.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
@RestController
@RequestMapping("feign")
public class FeignClientController {

    @Autowired
    ClientService feignService;

    @Autowired
    private EurekaClient client;

    @RequestMapping("test1")
    public String test1(){
        return feignService.test1("abc");
    }

    @RequestMapping("apps")
    public Applications getApps(){
        return client.getApplications();
    }

    @RequestMapping("client")
    public EurekaClient getClients(){
        return client;
    }

}
