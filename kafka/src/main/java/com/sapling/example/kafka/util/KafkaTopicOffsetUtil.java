package com.sapling.example.kafka.util;


import java.util.*;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import kafka.api.OffsetFetchRequest;
import kafka.api.OffsetFetchRequest$;
import kafka.api.OffsetFetchResponse;
import kafka.cluster.Broker;
import kafka.common.TopicAndPartition;
import kafka.consumer.SimpleConsumer;
import kafka.javaapi.PartitionMetadata;
import kafka.utils.ZkUtils;
import scala.collection.Iterable;
import scala.collection.JavaConversions;

import static com.sapling.example.kafka.util.KafkaUtil.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/5
 * @since v1.0
 */
public class KafkaTopicOffsetUtil {

    public static long getTopicOffset(String zkString, String topicName) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = getZkUtilsFromZkStr(zkString);
            List<Broker> brokers = getBrokers(zkUtils);
            brokers.forEach(item -> {
                System.out.println(item.endPoints().length());
                System.out.println(item.endPoints().head().host());
                System.out.println(item.endPoints().head().port());
            });
            //            AdminUtils.
            return 0;
        } finally {
            releaseZkUtil(zkUtils);
        }
    }


    /**
     * Get the single topic offset from kafka
     *
     * @param consumer  {@link SimpleConsumer}
     * @param topicName the name of topic
     * @param partition the partition number of topic
     * @param whichTime time of query
     * @return the offset information of topic
     */
    public static Map<Integer, Long> getTopicOffset(SimpleConsumer consumer, String topicName, Integer partition, long whichTime) {
        Map<String, Integer> topicAndPartition = new HashMap(1);
        topicAndPartition.put(topicName, partition);
        return getTopicsOffset(consumer, topicAndPartition, whichTime).get(topicName);
    }


    /**
     * Get the topic offset from kafka
     *
     * @param consumer  {@link SimpleConsumer}
     * @param topics    the partition of topic
     * @param whichTime time of query
     * @return the offset information of topic
     */
    public static Map<String, Map<Integer, Long>> getTopicsOffset(SimpleConsumer consumer, Map<String, Integer> topics, long whichTime) {
        String groupId = "";
        List<TopicAndPartition> requestInfo = new ArrayList<TopicAndPartition>();
        topics.entrySet().forEach(item -> {
            requestInfo.add(new TopicAndPartition(item.getKey(), item.getValue()));
        });
        Iterable<TopicAndPartition> iterable = JavaConversions.asScalaIterable(requestInfo);
        OffsetFetchRequest offsetFetchRequest = new OffsetFetchRequest(groupId,
                iterable.toSeq(), OffsetFetchRequest$.MODULE$.CurrentVersion(),
                0, OffsetFetchRequest$.MODULE$.DefaultClientId());
        //获取topic的offset
        OffsetFetchResponse response = consumer.fetchOffsets(offsetFetchRequest);
        Map<String, Map<Integer, Long>> resultMap = new HashMap<>(
                topics.size() << 1);
        //拼装结果
        JavaConversions.asJavaMap(response.requestInfoGroupedByTopic()).entrySet().forEach(item -> {
            Map valueMap = new HashMap(item.getValue().size() << 1);
            JavaConversions.asJavaMap(item.getValue()).entrySet().forEach(value -> {
                valueMap.put(value.getKey().partition(), value.getValue().offset());
            });
            resultMap.put(item.getKey(), valueMap);

        });
        return resultMap;
    }


    public static void seekToBeginning(String brokers, String groupId, String topicName) {
        KafkaConsumer<String, String> consumer = createConsumer(brokers, groupId);
        try {
            consumer.seekToBeginning(getTopicPartitions(consumer, topicName));
        } finally {
            consumer.close();
        }
    }

    public static void seekToEnd(String brokers, String groupId, String topicName) {
        KafkaConsumer<String, String> consumer = createConsumer(brokers, groupId);
        try {
            consumer.seekToEnd(getTopicPartitions(consumer, topicName));
        } finally {
            consumer.close();
        }
    }


    public static List<TopicPartition> getTopicPartitions(KafkaConsumer<String, String> consumer, String topicName) {
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        consumer.partitionsFor(topicName).forEach(item -> {
            topicPartitionList.add(new TopicPartition(item.topic(), item.partition()))
        });
        return topicPartitionList;
    }


}

