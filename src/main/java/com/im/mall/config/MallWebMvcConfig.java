//package com.im.mall.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 用于配置地址映射
// */
//// 是一个配置
//@Configuration
//public class MallWebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 允许访问static文件
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META_INF/resources/");
//        //文件磁盘图片url映射
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META_INF/resources/webjars/");
//
//    }
//}
