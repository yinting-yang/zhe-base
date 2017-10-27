package com.zhe.resp;

import java.io.Serializable;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 10:13 2017/10/27 0027
 */

public class Page implements Serializable{
    private Integer total;
    private Integer page;
    private Integer pageSize;

    public Integer getPageCount() {
        return this.getTotal().intValue() % this.getPageSize().intValue() == 0 ? Integer.valueOf((int) Math.floor((double) (this.getTotal().intValue() / this.getPageSize().intValue()))) : Integer.valueOf((int) Math.floor((double) (this.getTotal().intValue() / this.getPageSize().intValue())) + 1);
    }

    public Page(int page, int pageSize) {
        this.page = Integer.valueOf(page);
        this.pageSize = Integer.valueOf(pageSize);
    }

    public Page() {
        this(1, 10);
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
