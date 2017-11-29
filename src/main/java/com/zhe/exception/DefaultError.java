package com.zhe.exception;

public enum DefaultError implements ErrorInterface {
	// 仅异常消息
	MESSAGE_ERROR("-1", "{0}"),
	// 未定义异常--供自定义异常使用
	UNDEFINED_ERROR("-2", "undefined error"),
	// 未知异常
	UNKNOW_ERROR("-3", "unkonw error"),
	PROCESSING_ERROR("-4","请求处理中,请稍后再试");

	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	DefaultError(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}