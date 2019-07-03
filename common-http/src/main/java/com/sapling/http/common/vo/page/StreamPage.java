package com.sapling.http.common.vo.page;

/**
 * description: 流式获取分页数据，参数中只传开始记录数与获取的条数，适用于app
 *
 * @author wei.zhou
 * @date 2019/4/28 15:38
 */
public class StreamPage extends PageInfo {

    private long from;

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }


}
