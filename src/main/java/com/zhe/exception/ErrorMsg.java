package com.zhe.exception;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * 错误消息，包含错误码和消息参数
 * 
 *
 * @author huangsheng
 */
public class ErrorMsg implements Serializable {

	/**
	 * 错误对象：错误码+错误模版(支持参数)
	 */
	private ErrorInterface errorEnum;
	/**
	 * 错误消息参数
	 */
	private String[] params;

	public ErrorMsg() {
		super();
	}

	public ErrorMsg(ErrorInterface errorEnum) {
		super();
		this.errorEnum = errorEnum;
	}

	public ErrorMsg(ErrorInterface errorEnum, String param) {
		this.errorEnum = errorEnum;
		this.params = new String[] { param };
	}

	public ErrorMsg(ErrorInterface errorEnum, String[] params) {
		super();
		this.errorEnum = errorEnum;
		this.params = params;
	}

	public ErrorInterface getErrorEnum() {
		return errorEnum;
	}

	public void setErrorEnum(ErrorInterface errorEnum) {
		this.errorEnum = errorEnum;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getMessage() {
		if (errorEnum == null) {
			return "用户未定义异常";
		} else if (params == null && params.length < 1) {
			return errorEnum.getMsg();
		} else {
			return MessageFormat.format(errorEnum.getMsg(), params);
		}
	}

	public static void main(String args[]) {
		String pattern = "name:{0},sex:{1}";
		System.out.println(MessageFormat.format(pattern, null));
	}
}