package com.zhe.utils;

import java.security.MessageDigest;

/**
 * ClassName: MD5
 * Function:MD5加密
 * Reason: TODO ADD REASON(可选).
 * date: 2017年02月23日 17:06
 *
 * @author caijun.wei
 * @since JDK 1.7
 */
public class MD5 {

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String md5(String str) {
        if(str == null) {
            throw new NullPointerException("MD5加密失败:加密对象不能为空");
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    int i = b[offset];
                    if(i < 0) {
                        i+= 256;
                    }
                    if(i<16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
                return buf.toString();
            } catch (Exception e) {
                throw new RuntimeException("MD5加密失败:" + e.getMessage(), e);
            }
        }
    }
}
