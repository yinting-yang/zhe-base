package com.zhe.model;

import com.zhe.exception.ErrorInterface;

import java.io.Serializable;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 10:12 2017/10/27 0027
 */

public class ResModel<T> implements Serializable{
    private String code;
    private String msg;
    private T data;
    private Page page;

    public Object getErrData() {
        return errData;
    }

    public void setErrData(Object errData) {
        this.errData = errData;
    }

    private Object errData;

    public ResModel() {


    }

    public void success(T data) {
        this.code = "000000";
        this.msg = "成功";
        this.data = data;
    }

    public void failed(ErrorInterface status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public void error() {
        this.code = "999999";
        this.msg = "失败";
    }

    public ResModel(T data) {
        this.code ="000000";
        this.msg = "成功";
        this.data = data;
    }



    public ResModel(ErrorInterface state, T data) {
        this.code = state.getCode();
        this.msg = state.getMsg();
        this.data = data;
    }

    public void set(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void set(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String toString() {
        return "ResponseModel{code=\'" + this.code + '\'' + ", msg=\'" + this.msg + '\'' + ", data=" + this.data + ", page=" + this.page + '}';
    }

}
