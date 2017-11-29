package com.zhe.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author:zhe.yang
 * @Description:不能为null或者空字符串,只能作用在String上
 * @Date:11:11 2017/11/28 0028
 * @param: * @param null
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullAndEmpty {
    String scope() default "java.lang.String"; // 作用域

    String paramLimit() default "不能为null或者空字符串"; // 参数限制
}