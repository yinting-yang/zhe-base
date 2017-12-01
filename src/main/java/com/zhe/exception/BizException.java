package com.zhe.exception;

import java.text.MessageFormat;

/**
 * 自定义异常.
 *
 * @author huangsheng
 */
public class BizException extends RuntimeException {

    /**
     * 错误消息 (枚举类型+参数)
     */
    protected ErrorMsg errorMsg;

    public BizException() {
        super();
        this.errorMsg = new ErrorMsg(DefaultError.UNDEFINED_ERROR);
    }

    /**
     * 非枚举异常消息，套用默认消息模版
     *
     * @param message
     */
    public BizException(String message) {
        super();
        this.errorMsg = new ErrorMsg(DefaultError.MESSAGE_ERROR, message);
    }

    public BizException(String message, Throwable cause) {
        super();
        this.errorMsg = new ErrorMsg(DefaultError.MESSAGE_ERROR, message);
    }

    /**
     * 用户枚举异常，带模版
     *
     * @param errorEnum
     */
    public BizException(ErrorInterface errorEnum) {
        super();
        this.errorMsg = new ErrorMsg(errorEnum);
    }

    /**
     * 用户枚举异常，带模版，带参数
     *
     * @param errorEnum
     */
    public BizException(ErrorInterface errorEnum, String[] msgParams) {
        super();
        this.errorMsg = new ErrorMsg(errorEnum, msgParams);
    }

    /**
     * 用户枚举异常，带模版，带参数
     *
     * @param errorEnum
     */
    public BizException(ErrorInterface errorEnum, String msgParam) {
        super();
        this.errorMsg = new ErrorMsg(errorEnum, msgParam);
    }

    public BizException(ErrorInterface errorEnum, String[] msgParams, Throwable cause) {
        super(cause);
        this.errorMsg = new ErrorMsg(errorEnum, msgParams);

    }

    public BizException(ErrorInterface errorEnum, String msgParam, Throwable cause) {
        super(cause);
        this.errorMsg = new ErrorMsg(errorEnum, msgParam);
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorMsg.getErrorEnum().getCode();
    }

    public String getMessage() {
        return MessageFormat.format(this.getErrorMsg().getErrorEnum().getMsg(), this.getErrorMsg().getParams());
    }
}