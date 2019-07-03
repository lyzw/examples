package com.sapling.springcloud.example.client.feign;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/21
 * @since v1.0
 */
public class FeignFallBack implements FeignService{

    @Override
    public String test1(String name) {
        System.out.println("FeignFallBack");
        return "error";
    }

    @Override
    public String test2(String name) {
        return null;
    }
}
