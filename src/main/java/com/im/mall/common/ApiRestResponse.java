package com.im.mall.common;

import com.im.mall.exception.MallExceptionEnum;

/**
 * 通用返回对象
 */
public class ApiRestResponse <T> {
    private Integer status;
    private String msg;
    private T data;  // 请求返回
    private static final int OK_CODE = 10000; //约定10000请求正常
    private static final  String OK_MSG = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    public ApiRestResponse() {
        // 成功调用上面两参的构造函数
        this(OK_CODE, OK_MSG);
    }

    // 返回一个通用的响应对象 - 建立一个带着 10000 和 SUCCESS 信息的返回值 - 不需要其他信息
    // <T> ApiRestResponse<T> 返回类型
    public static <T> ApiRestResponse<T> success() {
        return new ApiRestResponse<>();

    }
    // 创建方法重载 返回succcess 和 返回值
    public static <T> ApiRestResponse<T> success(T result) {
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;

    }

    // 请求失败
    public static <T> ApiRestResponse<T> error(Integer code, String msg) {
        return new ApiRestResponse<>(code, msg);

    }

    // 传入异常枚举
    public static <T> ApiRestResponse<T> error(MallExceptionEnum ex) {
        return new ApiRestResponse<>(ex.getCode(), ex.getMsg());

    }

    // 打印方法也是生成的
    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
