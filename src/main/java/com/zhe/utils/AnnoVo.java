package com.zhe.utils;

import annotation.*;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 10:31 2017/11/28 0028
 */
@StringCut
public class AnnoVo {


/**
 * @Author:zhe.yang
 * @Description:
 * @Date:10:35 2017/11/29 0029
 * @param: * @param null
 */





    
    @Email
    @StringCut(minLength = 7, completion = "zxiaofan")
    private String guestEmail;

    @ToUpper
    private String toUpper;

    @StringLength(max = 5)
    private String cutName;

    @AssertFalse
    private boolean boolFalse;

    /**
     * 设置guestEmail.
     *
     * @return 返回guestEmail
     */
    public String getGuestEmail() {
        return guestEmail;
    }

    /**
     * 获取guestEmail.
     *
     * @param guestEmail 要设置的guestEmail
     */
    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    /**
     * 设置toUpper.
     *
     * @return 返回toUpper
     */
    public String getToUpper() {
        return toUpper;
    }

    /**
     * 获取toUpper.
     *
     * @param toUpper 要设置的toUpper
     */
    public void setToUpper(String toUpper) {
        this.toUpper = toUpper;
    }

    /**
     * 设置boolFalse.
     *
     * @return 返回boolFalse
     */
    public boolean isBoolFalse() {
        return boolFalse;
    }

    /**
     * 获取boolFalse.
     *
     * @param boolFalse 要设置的boolFalse
     */
    public void setBoolFalse(boolean boolFalse) {
        this.boolFalse = boolFalse;
    }

    /**
     * 设置cutName.
     *
     * @return 返回cutName
     */
    public String getCutName() {
        return cutName;
    }

    /**
     * 获取cutName.
     *
     * @param cutName 要设置的cutName
     */
    public void setCutName(String cutName) {
        this.cutName = cutName;
    }


}
