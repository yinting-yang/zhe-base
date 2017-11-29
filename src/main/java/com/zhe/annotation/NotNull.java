package com.zhe.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author:zhe.yang
 * @Description:不能为null,可以作用到任意非基础类型对象
 * @Date:11:10 2017/11/28 0028
 * @param: * @param null
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    String scope() default "非基础类型"; // 作用域

    String paramLimit() default "不能是null"; // 参数限制
}