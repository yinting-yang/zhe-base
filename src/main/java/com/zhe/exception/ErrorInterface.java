package com.zhe.exception;

/**
 * 用户自定义异常枚举需实现此接口，参考DefaultError
 * @author huangsheng
 */
public interface ErrorInterface {

	 String getCode();

	 String getMsg();
}
