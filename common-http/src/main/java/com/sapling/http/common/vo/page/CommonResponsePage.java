package com.sapling.http.common.vo.page;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/4/28 15:44
 */
public class CommonResponsePage extends CommonPage {

    private long totalCount;
    private long totalPage;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}
