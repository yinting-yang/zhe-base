package com.zhe.model;

import com.zhe.exception.ErrorInterface;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 13:42 2017/10/27 0027
 */

public class UserConstant {
    public static enum Status implements ErrorInterface {
        SUCCESS("000000", "成功"),
        FAILURE("666666", "失败"),
        ERROR("999999", "系统异常");
        private String code;
        private String msg;

        Status(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        @Override
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        @Override
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
