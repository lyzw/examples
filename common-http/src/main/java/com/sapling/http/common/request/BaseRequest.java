package com.sapling.http.common.request;

/**
 * @author wei.zhou02
 * @date 2019/4/28 15:33
 */
public class BaseRequest {



    private long timestamp;

    private int version;

    private Object data;

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

}
