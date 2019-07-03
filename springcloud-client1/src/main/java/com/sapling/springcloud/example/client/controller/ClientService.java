package com.sapling.springcloud.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sapling.springcloud.example.client.feign.FeignService;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/24
 * @since v1.0
 */
@Service
public class ClientService {
    @Autowired
    FeignService feignService;

    @HystrixCommand(fallbackMethod = "defaultStores")
    public String test1(String abc){
        return feignService.test1(abc);
    }

    public String defaultStores(String abc){
        return "fallback error";
    }

    @HystrixCommand(fallbackMethod = "defaultStores")
    public String test2(String abc){
        return feignService.test2(abc);
    }


}
