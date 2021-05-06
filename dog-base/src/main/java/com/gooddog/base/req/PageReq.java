package com.gooddog.base.req;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author zlh
 * @date 2021/4/29
 * @param
 * @return
 */
public class PageReq implements Serializable {
    private Integer pageNo = 1;
    private Integer pageSize = 10;

    public PageReq() {
    }

    public PageReq(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
