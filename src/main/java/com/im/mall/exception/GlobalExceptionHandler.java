package com.im.mall.exception;

import com.im.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 拦截参数异常 返回对应的错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody  // 接口返回Json 格式
    public  ApiRestResponse handleMethodArgumentNotVaildException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException:", e);

        return handleBindingResult(e.getBindingResult());

    }

    /**
     * 把异常处理为对外暴露的提示
     * @param result
     * @return
     */
    private ApiRestResponse handleBindingResult(BindingResult result)  {
        List<String> list = new ArrayList<>();
        if(result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for(ObjectError objectError: allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }

        // 如果list 为null
        if(list.size() == 0) {
            return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }
}
