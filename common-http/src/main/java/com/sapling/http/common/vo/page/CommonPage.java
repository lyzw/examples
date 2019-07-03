package com.sapling.http.common.vo.page;

/**
 * description: 普通分页条件，接口上送页码与页面记录数，适用于PC分页
 *
 * @author wei.zhou
 * @date 2019/4/28 15:35
 */
public class CommonPage extends PageInfo {

    private int page;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


}
