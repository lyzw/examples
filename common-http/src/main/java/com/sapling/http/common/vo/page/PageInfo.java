package com.sapling.http.common.vo.page;

import java.io.Serializable;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/4/28 15:53
 */
public class PageInfo implements Serializable {

    private int pageSize = 20;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
