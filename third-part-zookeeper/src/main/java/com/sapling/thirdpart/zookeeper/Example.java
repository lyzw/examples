package com.sapling.thirdpart.zookeeper;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/4
 * @since v1.0
 */
public class Example {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.21.51:2181");
        List<String> list = zkClient.getChildren("/marathon/state/instance");
        list.forEach(f->{
            System.out.println(f);
        });
    }
}
