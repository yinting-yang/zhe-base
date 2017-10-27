package com.zhe.utils;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 10:56 2017/10/27 0027
 */

public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str:
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断字符串是否为空或空窜
     *
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * HTTP参数安全过滤
     *
     * @param str
     * @return
     */
    public static String htmlFormat(String str) {
        if (isTrimEmpty(str)) {
            return str;
        } else {
            return str.replaceAll("<", "＜").replaceAll(">", "＞");
        }
    }
}
