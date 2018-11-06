package com.sapling.example.kafka.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kafka.cluster.Broker;
import kafka.cluster.Cluster;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/5
 * @since v1.0
 */
public class KafkaUtilTest {
    private static final String zkString = "192.168.32.43:2181";

    @Test
    public void getCluster() {
        Cluster cluster = KafkaUtil.getCluster(new ZookeeperConfig(zkString));
    }

    @Test
    public void getBrokers() {
        List brokers = KafkaUtil.getBrokers(new ZookeeperConfig(zkString));
        System.out.println(brokers);
    }

    @Test
    public void fetchEntityConfig() {
    }

    @Test
    public void getZkUtilsFromZkStr() {
    }

    @Test
    public void getZkUtilsFromZkStr1() {
    }
}