package com.zhe.annotation;

import java.lang.annotation.Inherited;


/**
 * @Author:zhe.yang
 * @Description:其他注解尽量实现此注解的方法.
 * @Date:11:10 2017/11/28 0028
 * @param: * @param null
 */

@Inherited // 可被继承
public @interface BaseAnno {
    String scope() default ""; // 作用域

    String paramLimit() default ""; // 参数限制
}