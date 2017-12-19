package com.zhe.model;

import java.io.Serializable;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 15:44 2017/12/8 0008
 */

public class ReqModel<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -8216120115091027858L;
    private Integer page;
    private Integer pageSize;
    private String reqStr;
    private T param;

    public ReqModel() {
    }

    public static long getSerialVersionUID() {
        return -8216120115091027858L;
    }

    public void setReqStr(String reqStr) {
        this.reqStr = reqStr;
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

    public String getReqStr() {
        return this.reqStr;
    }

    public T getParam() {
        return this.param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public String toString() {
        return "RequestModel [page=" + this.page + ", pageSize=" + this.pageSize + ", reqStr=" + this.reqStr + ", param=" + this.param + "]";
    }
}

