package com.sapling.http.common.response;

import java.io.Serializable;

/**
 * description: 基础返回
 *
 * @author wei.zhou
 * @date 2019/4/28 16:02
 */
public class BaseResponse implements Serializable {
    private String code;
    private String message;


    private int version;
    private long timstamp;
    private Object data;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getTimstamp() {
        return timstamp;
    }

    public void setTimstamp(long timstamp) {
        this.timstamp = timstamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
