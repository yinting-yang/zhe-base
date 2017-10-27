package com.zhe.utils.web;

import com.zhe.utils.RedisUtil;
import com.zhe.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @Author:zhe.yang
 * @Description:点单登录存放redis
 * @DATE:Created in 11:05 2017/10/27 0027
 */

public class SessionUtil {
    public static Object getSession(HttpServletRequest request, String key) {
        if (StringUtil.isTrimEmpty(key)) {
            throw new NullPointerException("获取session的失败:key不能为空");
        }
        HttpSession session = request.getSession(true);
        String id = session.getId();
        return RedisUtil.get(id + "_" + key.trim());
    }

    /**
     * 添加session内容
     *
     * @param request
     * @param key:session的key值
     * @param value:
     */
    public static void addSession(HttpServletRequest request, String key, Serializable value) {
        if (StringUtil.isTrimEmpty(key)) {
            throw new NullPointerException("添加session的失败:key不能为空");
        }
        if (value == null) {
            delSession(request, key.trim());
        } else {
            HttpSession session = request.getSession(true);
            String id = session.getId();
            RedisUtil.set(id + "_" + key.trim(), value, 60 * 60 * 5);
        }
    }

    /**
     * 删除session的内容
     *
     * @param request
     * @param key
     */
    public static void delSession(HttpServletRequest request, String key) {
        if (StringUtil.isTrimEmpty(key)) {
            throw new NullPointerException("删除session的失败:key不能为空");
        }
        HttpSession session = request.getSession(true);
        String id = session.getId();
        RedisUtil.del(id + "_" + key.trim());
    }
}
