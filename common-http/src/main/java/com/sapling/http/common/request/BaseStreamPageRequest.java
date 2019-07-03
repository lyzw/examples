package com.sapling.http.common.request;

import com.sapling.http.common.vo.page.StreamPage;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/4/28 16:00
 */
public class BaseStreamPageRequest {

    StreamPage page;

    public StreamPage getPage() {
        return page;
    }

    public void setPage(StreamPage page) {
        this.page = page;
    }
}
