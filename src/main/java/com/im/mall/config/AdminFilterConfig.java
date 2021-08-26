package com.im.mall.config;

import com.im.mall.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * admin 过滤器的配置
 */
@Configuration
public class AdminFilterConfig {

    @Bean // 让spring 识别
    public AdminFilter adminFilter() {
        return new AdminFilter();
    }

    /**
     * 添加到filter 链路
     * @return
     */
    @Bean(name="adminFilterConf") // 不能和上面重名
    public FilterRegistrationBean adminFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(adminFilter());
        // 需要拦截的
        filterRegistrationBean.addUrlPatterns("/admin/category/*");
        filterRegistrationBean.addUrlPatterns("/admin/product/*");
        filterRegistrationBean.addUrlPatterns("/admin/order/*");
        filterRegistrationBean.setName("adminFilterConfig");

        return filterRegistrationBean;
    }
}
