package com.im.mall.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 打印请求和响应信息
 */
@Aspect
@Component  // 让spring识别
public class WebLogAspect {
    // 打印日志的方法
    private final Logger log =  LoggerFactory.getLogger(WebLogAspect.class);

    // 拦截controller 里所有的
    @Pointcut("execution(public * com.im.mall.controller.*.*(..))")
    public void webLog() {

    }

    // 请求之前
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 收到请求， 记录请求内容
        // RequestContextHolder 持有上下文的Request容器
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        //拿到当前的请求
        HttpServletRequest request = attributes.getRequest();

        log.info("URL:" + request.getRequestURL().toString());
        log.info("HTTP_METHOD:" + request.getMethod());
        log.info("IP:" + request.getRemoteAddr());
        log.info("CLASS_METHOD" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数ARGS: " + Arrays.toString(joinPoint.getArgs()));

    }

    //请求响应返回
    @AfterReturning(returning = "res", pointcut = "webLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        // 处理完请求返回内容
        // fastJson 对象转成Json 的工具
        log.info("RESPONSE: " +  new ObjectMapper().writeValueAsString(res));
    }
}
