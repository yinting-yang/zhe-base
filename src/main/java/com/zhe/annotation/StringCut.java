package com.zhe.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @Author:zhe.yang
 * @Description:数据超长截取,注解于待截取字段及其类、泛型
 * @Date:11:14 2017/11/28 0028
 * @param: * @param null
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface StringCut {

    // 最大长度
    int maxLength() default 0;

    // 最小长度
    int minLength() default 0;

    // length小于最小长度，用该字符补上，默认"0"
    String completion() default "0";

    // 保留头部
    boolean isKeepHead() default true;

    // // 泛型
    // String isGeneric() default "";

    // 参数或者字段描述,这样能够显示友好的异常信息
    String description() default "";

}