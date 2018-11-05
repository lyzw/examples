package com.sapling.example.kafka.util;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.security.JaasUtils;

import kafka.admin.AdminUtils;
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

    protected static Properties fetchEntityConfig(ZkUtils zk, KafkaEntityTypeEnum typeEnum, String name) {
        return AdminUtils.fetchEntityConfig(zk, typeEnum.getTypeName(), name);
    }





    protected static void releaseZkUtil(ZkUtils zkUtils) {
        if (zkUtils != null) {
            try {
                zkUtils.close();
                zkUtils = null;
            } catch (Exception e) {

            }
        }
    }

    protected static ZkUtils getZkUtilsFromZkStr(ZookeeperConfig zk) {
        return ZkUtils.apply(zk.getZkUrl(), zk.getSessionTimeout(), zk.getSessionTimeout(), JaasUtils.isZkSecurityEnabled());
    }
}




