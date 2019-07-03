package com.sapling.http.common.vo.page;

/**
 * description: 流式分页查询结果，只返回pageSize，from与totalCount
 *
 * @author wei.zhou
 * @date 2019/4/28 15:50
 */
public class StreamResponsePage extends StreamPage {
    private long totalCount;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
