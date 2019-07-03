package com.sapling.tools.thirdpart.zookeeper;

import java.util.regex.Pattern;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/13
 * @since v1.0
 */
public class ZookeeperUtil {


    public static String set(ZooKeeper zooKeeper, String path, byte[] value, CreateMode createMode) throws KeeperException, InterruptedException {
        // 判断当前的path是否存在
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            return zooKeeper.create(path, value, null, createMode);
        } else {
            return zooKeeper.setData(path, value, stat.getVersion()).toString();
        }
    }

}
