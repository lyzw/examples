package com.sapling.http.common.vo.page;

import java.io.Serializable;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/4/28 15:45
 */
public class ResponsePage implements Serializable {

    private long totalCount;

    private int pageSize;


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
