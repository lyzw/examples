package com.sapling.example.kafka.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/5
 * @since v1.0
 */
public class KafkaTopicOffsetUtilTest {
    private static final String zkString = "192.168.32.43:2181";

    @Test
    public void getTopicOffset() {
        KafkaTopicOffsetUtil.getTopicOffset(zkString,"ga20180614174152643187353140019200-ga20180614174152643187353140019200-changelog");
    }
}