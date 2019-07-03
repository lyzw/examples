package com.sapling.tools.thirdpart.kafka;

import java.util.Properties;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/31
 * @since v1.0
 */
public class TopicInfo {
    private String topicName;
    private Integer partitions = 1;
    private Integer replicationFactor = 1;
    private Properties topicConfig = new Properties();

    public TopicInfo(String topicName){
        this.topicName = topicName;
    }

    public TopicInfo(String topicName, Integer partitions, Integer replicationFactor, Properties topicConfig) {
        this.topicName = topicName;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
        this.topicConfig = topicConfig;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getPartitions() {
        return partitions;
    }

    public void setPartitions(Integer partitions) {
        this.partitions = partitions;
    }

    public Integer getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Integer replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    public Properties getTopicConfig() {
        return topicConfig;
    }

    public void setTopicConfig(Properties topicConfig) {
        this.topicConfig = topicConfig;
    }
}
