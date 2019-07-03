package com.sapling.tools.thirdpart.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import kafka.admin.AdminClient;
import scala.collection.JavaConversions;

import static com.sapling.tools.thirdpart.kafka.KafkaUtil.makeConsumerProperties;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/6
 * @since v1.0
 */
public class KafkaBrokersTopicUtil {


    public static Map<TopicPartition, Long> getAllTopicOffset(String brokers, String groupId) {
        Map<TopicPartition, Long> result = new HashMap();
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(brokers);
            JavaConversions.asJavaMap(adminClient.listGroupOffsets(groupId)).forEach((key, value) -> {
                result.put(key, (Long) value);
            });
            return result;
        } finally {
            adminClient.close();
        }
    }

    public static Map<TopicPartition, Long> getTopicOffset(String  brokers, String groupId, String topicName) {
        Map<TopicPartition, Long> result = new HashMap();
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(brokers);
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

    public static Map listAllConsumerGroups(String brokers) {
        AdminClient adminClient = null;
        try {
            adminClient = AdminClient.createSimplePlaintext(brokers);
            return JavaConversions.asJavaMap(adminClient.listAllConsumerGroups());
        } finally {
            adminClient.close();
        }
    }

    public static Set<String> listAllTopic(String brokers, String groupId) {
        Properties properties = makeConsumerProperties(brokers, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        try {
            return consumer.listTopics().keySet();
        } finally {
            consumer.close();
        }
    }
}
