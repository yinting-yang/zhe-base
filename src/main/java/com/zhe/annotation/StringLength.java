package com.zhe.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author:zhe.yang
 * @Description:字符串长度校验
 * @Date:11:14 2017/11/28 0028
 * @param: * @param null
 */

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringLength {
    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String scope() default "java.lang.String"; // 作用域

    String paramLimit() default "长度不在区间范围内"; // 参数限制
}
