package com.sapling.tools.thirdpart.kafka;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.security.JaasUtils;

import kafka.admin.AdminUtils;
import kafka.cluster.Broker;
import kafka.cluster.Cluster;
import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/31
 * @since v1.0
 */
public class KafkaUtil {


    public static Cluster getCluster(ZookeeperConfig zookeeperConfig) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zookeeperConfig);
            return zkUtils.getCluster();
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    public static List<Broker> getBrokers(ZookeeperConfig zookeeperConfig) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zookeeperConfig);
            return getBrokers(zkUtils);
        } finally {
            releaseZkUtil(zkUtils);
        }
    }

    public static List<Broker> getBrokers(ZkUtils zkUtils) {
        return JavaConversions.asJavaList(zkUtils.getAllBrokersInCluster());
    }


    public static Properties fetchEntityConfig(ZkUtils zk, KafkaEntityTypeEnum typeEnum, String name) {
        return AdminUtils.fetchEntityConfig(zk, typeEnum.getTypeName(), name);
    }


    public static void releaseZkUtil(ZkUtils zkUtils) {
        if (zkUtils != null) {
            try {
                zkUtils.close();
                zkUtils = null;
            } catch (Exception e) {

            }
        }
    }


    public static ZkUtils getZkUtilsFromZkStr(String zk) {
        return getZkUtilsFromZkStr(new ZookeeperConfig(zk));
    }

    public static ZkUtils getZkUtilsFromZkStr(ZookeeperConfig zk) {
        return ZkUtils.apply(zk.getZkUrl(), zk.getSessionTimeout(), zk.getSessionTimeout(), JaasUtils.isZkSecurityEnabled());
    }

    public static Properties makeConsumerProperties(String brokers, String groupId) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "10000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return properties;
    }


    public static KafkaConsumer<String,String> createConsumer(String brokers, String groupId){
        Properties properties = makeConsumerProperties(brokers, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        return consumer;
    }
}




