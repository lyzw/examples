package com.sapling.example.kafka.util;

import java.util.List;
import java.util.Properties;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;

import static com.sapling.example.kafka.util.KafkaUtil.*;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/31
 * @since v1.0
 */
public class KafkaTopicUtil {


    /**
     * fetch all of the topics
     *
     * @param zk zookeeper configuration
     * @return topic list
     */
    public static List<String> fetchAllTopics(ZookeeperConfig zk) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            return JavaConversions.asJavaList(zkUtils.getAllTopics());
        } finally {
            releaseZkUtil(zkUtils);
        }
    }


    /**
     * create topic
     *
     * @param zkUrl     the url of zookeeper
     * @param topicInfo the kafka topic configuration {@link TopicInfo }
     */
    public static void createTopic(String zkUrl, TopicInfo topicInfo) {
        createTopic(new ZookeeperConfig(zkUrl), topicInfo);
    }

    /**
     * create topic with the url of zookeeper and the name of topic
     *
     * @param zkUrl     the url of zookeeper
     * @param topicName The name of topic
     */
    public static void createTopic(String zkUrl, String topicName) {
        createTopic(new ZookeeperConfig(zkUrl), new TopicInfo(topicName));
    }


    /**
     * create topic
     *
     * @param zk        the zookeeper configuration
     * @param topicInfo the kafka topic configuration {@link TopicInfo }
     */
    public static void createTopic(ZookeeperConfig zk, TopicInfo topicInfo) {
        ZkUtils zkUtils = null;
        try {
            if (isTopicExists(zk, topicInfo.getTopicName())) {
                throw new TopicExistsException();
            }
            zkUtils = getZkUtilsFromZkStr(zk);
            AdminUtils.createTopic(zkUtils, topicInfo.getTopicName(),
                                   topicInfo.getPartitions(), topicInfo.getReplicationFactor(),
                                   topicInfo.getTopicConfig(), RackAwareMode.Enforced$.MODULE$);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }


    /**
     * delete topic from kafka
     *
     * @param zk        the zookeeper configuration
     * @param topicName the name of topic
     */
    public static void deleteTopic(ZookeeperConfig zk, String topicName) {
        ZkUtils zkUtils = null;
        try {
            if (!isTopicExists(zk, topicName)) {
                throw new TopicNotExistsException();
            }
            zkUtils = getZkUtilsFromZkStr(zk);
            AdminUtils.deleteTopic(zkUtils, topicName);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * delete topic from kafka
     *
     * @param zkUrl     the url of zookeeper
     * @param topicName the name of topic
     */
    public static void deleteTopic(String zkUrl, String topicName) {
        deleteTopic(new ZookeeperConfig(zkUrl), topicName);
    }

    /**
     * change the properties of topic
     *
     * @param zk         zookeeper configuration
     * @param topicName  name of topic
     * @param properties new properties of topic
     */
    public static void changeTopicProperties(ZookeeperConfig zk, String topicName, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            checkTopicIsExists(zkUtils, topicName);
            AdminUtils.changeTopicConfig(zkUtils, topicName, properties);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * change the properties of topic
     *
     * @param zkUrl      url of zookeeper
     * @param topicName  name of topic
     * @param properties new properties of topic
     */
    public static void changeTopicProperties(String zkUrl, String topicName, Properties properties) {
        changeTopicProperties(new ZookeeperConfig(zkUrl), topicName, properties);
    }

    /**
     * fetch the configuration of topic
     *
     * @param zkUrl     url of zookeeper
     * @param topicName name of topic
     * @return the properties of topic
     */
    public static Properties fetchTopicConfig(String zkUrl, String topicName) {
        return fetchTopicConfig(new ZookeeperConfig(zkUrl), topicName);
    }


    /**
     * fetch the configuration of topic
     *
     * @param zk        configuration of zookeeper
     * @param topicName name of topic
     * @return the properties of topic
     */
    public static Properties fetchTopicConfig(ZookeeperConfig zk, String topicName) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            checkTopicIsExists(zkUtils, topicName);
            return fetchEntityConfig(zkUtils, KafkaEntityTypeEnum.ENTITY_TYPE_TOPIC, topicName);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }


    /**
     * reset the partition of topic
     * Notice that the new number of partition must larger than the older .
     *
     * @param zk              configuration of zookeeper
     * @param topicName       name of topic
     * @param partitionNumber The number of topic partition
     */
    public static void setTopicPartition(ZookeeperConfig zk, String topicName, Integer partitionNumber) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            AdminUtils.addPartitions(zkUtils, topicName, partitionNumber, "", true, RackAwareMode.Enforced$.MODULE$);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * get the topic list of the kafka
     *
     * @param zk configuration of zookeeper
     * @return topic list of kafka
     */
    public static List<String> getAllTopics(ZookeeperConfig zk) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            return JavaConversions.asJavaList(zkUtils.getAllTopics());
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * check if the topic is already exists in kafka
     *
     * @param zk        zookeeper configuration
     * @param topicName name of topic
     * @return
     */
    public static boolean isTopicExists(ZookeeperConfig zk, String topicName) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            return AdminUtils.topicExists(zkUtils, topicName);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * check if the topic is already exists in kafka
     *
     * @param zkUtils   zookeeper util
     * @param topicName name of topic
     * @return
     */
    private static boolean isTopicExists(ZkUtils zkUtils, String topicName) {
        return AdminUtils.topicExists(zkUtils, topicName);
    }

    public static void checkTopicIsExists(ZkUtils zkUtils, String topicName) {
        if (!isTopicExists(zkUtils, topicName)) {
            throw new TopicNotExistsException();
        }
    }


    public static List<String> getAllTopicsForConsumerGroup(ZookeeperConfig zk, String consumerGroup) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zk);
            return JavaConversions.asJavaList(zkUtils.getTopicsByConsumerGroup(consumerGroup));
        } finally {
            releaseZkUtil(zkUtils);
        }
    }
}
