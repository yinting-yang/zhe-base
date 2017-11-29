package com.zhe.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:zhe.yang
 * @Description:断言为false 该注解只能是boolean和Boolean使用
 * @Date:11:09 2017/11/28 0028
 * @param: * @param null
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertFalse {
    String scope() default "boolean或java.lang.Boolean"; // 作用域

    String paramLimit() default "只能为false"; // 参数限制
}