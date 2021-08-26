package com.im.mall.exception;

/**
 * 统一MallException
 * 继承 RuntimeException 不需要额外的try Catch
 */
public class MallException extends RuntimeException {
    private  final Integer code;
    private  final String message;

    public MallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 用枚举来初始化构造函数

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MallException(MallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }
}
