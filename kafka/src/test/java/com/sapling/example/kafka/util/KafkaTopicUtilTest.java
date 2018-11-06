package com.sapling.example.kafka.util;

import java.util.List;

import org.junit.Test;

import kafka.admin.AdminClient;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/6
 * @since v1.0
 */
public class KafkaTopicUtilTest {
    private static final String zkString = "192.168.32.43:2181";


    @Test
    public void test(){
        AdminClient adminClient = AdminClient.createSimplePlaintext(KafkaTopicUtil.getBrokerEndpointStr(new ZookeeperConfig(zkString)));
        System.out.println(adminClient.bootstrapBrokers());
        System.out.println(adminClient.listGroupOffsets("EOI_Curator_Consumer_Group"));
        System.out.println(adminClient.listAllGroups());
    }


    @Test
    public void testGetBrokerEndpoint(){
        List<String> list = KafkaTopicUtil.getBrokerEndpoint(new ZookeeperConfig(zkString));
        list.forEach(s-> System.out.println(s));
    }


    @Test
    public void testGetBrokerEndpointStr(){
        String s = KafkaTopicUtil.getBrokerEndpointStr(new ZookeeperConfig(zkString));
        System.out.println(s);
    }

    @Test
    public void testGetAllTopicOffset(){
        System.out.println(KafkaTopicUtil.getAllTopicOffset(new ZookeeperConfig(zkString),"1111111111111"));
    }

    @Test
    public void testGetTopicOffset(){
        System.out.println(KafkaTopicUtil.getTopicOffset(new ZookeeperConfig(zkString),"itoabase_HUB","mave133_fc20180409150606122"));
    }


    @Test
    public void testGetAllTopicAndPartition(){
//        System.out.println(KafkaConsumerUtil.getAllConsumerGroupsForTopic(new ZookeeperConfig(zkString),"mave133_fc20180409150606122"));
//        KafkaTopicUtil.getAllTopicAndPartition(new ZookeeperConfig(zkString)).forEach((key,value)->{
//            if(value>0){
//                System.out.println(key + ":" +value);
//            }
//        });
    }


}