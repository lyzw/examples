package com.sapling.example.kafka.util;

import java.util.*;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import kafka.admin.AdminClient;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.JavaConverters;

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
            return getAllTopics(zkUtils);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * get the topic list of the kafka
     *
     * @param zkUtils configuration of zookeeper
     * @return topic list of kafka
     */
    public static List<String> getAllTopics(ZkUtils zkUtils) {
        return JavaConversions.asJavaList(zkUtils.getAllTopics());
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


    public static Map<String, Integer> getAllTopicAndPartition(ZookeeperConfig zookeeperConfig) {
        Map<String, Integer> result = new HashMap<>();
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zookeeperConfig);
            JavaConversions.asJavaSet(zkUtils.getAllPartitions()).forEach(item -> {
                result.put(item.topic(), item.partition());
            });
            return result;
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * get the endpoint of the brokers(pattern like "10.10.0.1:9092,10.10.0.2:9092")
     *
     * @param zookeeperConfig the configuration of zookeeper{@link ZookeeperConfig}
     * @return endpoint of the brokers
     */
    public static String getBrokerEndpointStr(ZookeeperConfig zookeeperConfig) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zookeeperConfig);
            StringBuilder sb = new StringBuilder();
            getBrokers(zkUtils).forEach(item -> {
                JavaConversions.asJavaList(item.endPoints()).forEach(value -> {
                    sb.append(value.host()).append(":").append(value.port()).append(",");
                });
            });
            return sb.substring(0, sb.length() - 1);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    /**
     * get the endpoint list of the brokers
     *
     * @param zookeeperConfig the configuration of zookeeper{@link ZookeeperConfig}
     * @return endpoint list of the brokers
     */
    public static List<String> getBrokerEndpoint(ZookeeperConfig zookeeperConfig) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zookeeperConfig);
            List<String> result = new ArrayList();
            getBrokers(zkUtils).forEach(item -> {
                JavaConversions.asJavaList(item.endPoints()).forEach(value -> {
                    result.add(value.host() + ":" + value.port());
                });
            });
            return result;
        } finally {
            releaseZkUtil(zkUtils);
        }
    }


    /**
     * get
     *
     * @param zookeeperConfig
     * @param groupId
     * @return
     */
    public static Map<TopicPartition, Long> getAllTopicOffset(ZookeeperConfig zookeeperConfig, String groupId) {
        Map<TopicPartition, Long> result = new HashMap();
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(getBrokerEndpointStr(zookeeperConfig));
            JavaConversions.asJavaMap(adminClient.listGroupOffsets(groupId)).forEach((key, value) -> {
                result.put(key, (Long) value);
            });
            return result;
        } finally {
            adminClient.close();
        }
    }

    public static Map<TopicPartition, Long> getTopicOffset(ZookeeperConfig zookeeperConfig, String groupId, String topicName) {
        Map<TopicPartition, Long> result = new HashMap();
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(getBrokerEndpointStr(zookeeperConfig));
            JavaConversions.asJavaMap(adminClient.listGroupOffsets(groupId)).forEach((key, value) -> {
                if (key.topic().equals(topicName)) {
                    result.put(key, (Long) value);
                }
            });
            return result;
        } finally {
            adminClient.close();
        }
    }

    public static Map listAllConsumerGroups(ZookeeperConfig zookeeperConfig) {
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(getBrokerEndpointStr(zookeeperConfig));
            return JavaConversions.asJavaMap(adminClient.listAllConsumerGroups());
        } finally {
            adminClient.close();
        }
    }

    public static Map listAllConsumerGroupsByBrokers(String brokers) {
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(brokers);
            return JavaConversions.asJavaMap(adminClient.listAllConsumerGroups());
        } finally {
            adminClient.close();
        }
    }

    public static Set<String> listAllTopicFromBrokers(String brokers, String groupId) {
        Properties properties = makeConsumerProperties(brokers, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        try {
            return consumer.listTopics().keySet();
        } finally {
            consumer.close();
        }
    }





}
