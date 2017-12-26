package com.zhe.exception;

public enum DefaultError implements ErrorInterface {
	// 仅异常消息
	ERROR("999999", "失败"),
	SUCCEED("000000", "成功"),
	MESSAGE_ERROR("-1", "{0}"),
	// 未定义异常--供自定义异常使用
	UNDEFINED_ERROR("-2", "undefined error"),
	// 未知异常
	UNKNOW_ERROR("-3", "unkonw error"),
	PROCESSING_ERROR("-4","请求处理中,请稍后再试");

	private String code;
	private String msg;

	@Override
	public String getCode() {
		return code;
	}

	DefaultError(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	@Override
	public String getMsg() {

		return msg;
	}
}