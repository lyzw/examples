package com.sapling.http.common.request;

import com.sapling.http.common.vo.page.CommonPage;

/**
 * @author wei.zhou02
 * @date 2019/4/28 15:35
 */
public class BaseCommonPageRequest extends BaseRequest {

    private CommonPage page;

    public CommonPage getPage() {
        return page;
    }

    public void setPage(CommonPage page) {
        this.page = page;
    }

}
