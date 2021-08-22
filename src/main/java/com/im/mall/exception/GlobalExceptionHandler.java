package com.im.mall.exception;

import com.im.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理统一异常的handler   - 拦截所有异常
 */

@ControllerAdvice  // 用于拦截异常
public class GlobalExceptionHandler {
    // 打印日志 - 传入当前类
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // Exception.class 规定处理哪种异常

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody  // 接口返回Json 格式
    public Object handleException(Exception e) {
        log.error("Default Exception:", e);
        return ApiRestResponse.error(MallExceptionEnum.SYSTEM_ERROR);

    }


    /**
     * 业务系统异常
     */
    @ExceptionHandler(MallException.class)
    @ResponseBody  // 接口返回Json 格式
    public Object handleMallException(MallException e) {
        log.error("MallException Exception:", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());

    }
}
