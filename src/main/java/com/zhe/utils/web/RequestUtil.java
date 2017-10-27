package com.zhe.utils.web;

import com.zhe.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:zhe.yang
 * @Description:
 * @DATE:Created in 10:53 2017/10/27 0027
 */

public class RequestUtil {
    private final static String[] heads = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP"};

    public static String getIp(HttpServletRequest request) {
        String ip = getProxyIp(request);
        if (!StringUtil.isTrimEmpty(ip)) {
            if (ip.contains(",")) {
                return ip.split(",")[0].trim();
            } else {
                return ip.trim();
            }
        } else {
            return null;
        }
    }

    public static String getProxyIp(HttpServletRequest request) {
        if (request == null) {
            throw new NullPointerException("request不能为空!");
        }

        for (String haed : heads) {
            String ip = request.getHeader(haed);
            if (!StringUtil.isTrimEmpty(ip)) {
                if (!"unknown".equalsIgnoreCase(ip.trim())) {
                    return ip;
                }
            }
        }
        return request.getRemoteAddr();

    }
}
