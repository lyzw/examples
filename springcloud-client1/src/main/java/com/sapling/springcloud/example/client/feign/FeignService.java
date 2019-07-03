package com.sapling.springcloud.example.client.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
@FeignClient(value = "feignExample", fallback = FeignFallBack.class)
public interface FeignService {

    @RequestMapping(value = "/example/test1", method = RequestMethod.GET)
    String test1(@RequestParam("name") String name);

    @RequestMapping(value = "/example/test2", method = RequestMethod.GET)
    String test2(@RequestParam("name") String name);

}
