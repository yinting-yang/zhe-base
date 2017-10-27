package com.zhe.model;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 13:34 2017/10/27 0027
 */

public class ZheException extends RuntimeException {
    private SysState sysState;

    public ZheException(SysState sysState) {
        super(sysState.getMsg());
        this.sysState = sysState;
    }

    public ZheException(SysState sysState, Throwable throwable) {
        super(sysState.getMsg(), throwable);
        this.sysState = sysState;
    }

    public SysState getSysState() {
        return sysState;
    }

    public void setSysState(SysState SysState) {
        this.sysState = SysState;
    }
}
