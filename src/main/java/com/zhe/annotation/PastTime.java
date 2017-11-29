package com.zhe.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author:zhe.yang
 * @Description:必须是过去某个时刻, 该注解只能是java.util.Date/long/java.long.Long/java.sql.Timestamp
 * @Date:11:12 2017/11/28 0028
 * @param: * @param null
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PastTime {
    String scope() default "java.util.Date"; // 作用域

    String paramLimit() default "必须是过去某个时间"; // 参数限制
}