package com.zhe.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author:zhe.yang
 * @Description:必须是将来某个时刻,该注解只能是java.util.Date/long/java.long.Long/java.sql.Timestamp
 * @DATE:Created in 20:35 2017/11/27 0027
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureTime {
    String scope() default "java.util.Date"; // 作用域

    String paramLimit() default "必须是未来某个时间"; // 参数限制
}
