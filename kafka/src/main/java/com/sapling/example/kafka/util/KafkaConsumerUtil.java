package com.sapling.example.kafka.util;

import java.util.Set;

import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;

import static com.sapling.example.kafka.util.KafkaTopicUtil.*;
import static com.sapling.example.kafka.util.KafkaUtil.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/31
 * @since v1.0`
 */
public class KafkaConsumerUtil {


    public static Set<String> getAllConsumerGroupsForTopic(ZookeeperConfig zk, String topicName){
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            checkTopicIsExists(zkUtils, topicName);
            return JavaConversions.asJavaSet(zkUtils.getAllConsumerGroupsForTopic(topicName));
        } finally {
            releaseZkUtil(zkUtils);
        }
    }


}