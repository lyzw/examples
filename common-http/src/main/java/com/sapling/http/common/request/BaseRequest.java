package com.sapling.http.common.request;

/**
 * @author zhouwei
 * @date 2019/4/28 15:33
 */
public class BaseRequest {



    private long timestamp;

    private int version;

    private Object data;

    private String sign;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
