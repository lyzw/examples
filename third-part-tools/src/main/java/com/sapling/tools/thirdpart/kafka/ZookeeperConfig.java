package com.sapling.tools.thirdpart.kafka;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/10/31
 * @since v1.0
 */
public class ZookeeperConfig {
    private String zkUrl;
    private Integer sessionTimeout = 30000;
    private Integer connectionTimeout = 30000;

    public ZookeeperConfig(String zkUrl) {
        this.zkUrl = zkUrl;
    }

    public ZookeeperConfig(String zkUrl, Integer sessionTimeout, Integer connectionTimeout) {
        this.zkUrl = zkUrl;
        this.sessionTimeout = sessionTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    public String getZkUrl() {
        return zkUrl;
    }

    public void setZkUrl(String zkUrl) {
        this.zkUrl = zkUrl;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
