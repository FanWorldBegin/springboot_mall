package com.im.mall.config;

import com.im.mall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户 过滤器的配置
 */
@Configuration
public class UserFilterConfig {

    @Bean // 让spring 识别
    public UserFilter userFilter() {
        return new UserFilter();
    }

    /**
     * 添加到filter 链路
     * @return
     */
    @Bean(name="userFilterConf") // 不能和上面重名
    public FilterRegistrationBean userFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(userFilter());
        // 需要拦截的
        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/order/*");
        filterRegistrationBean.setName("userFilterConf");

        return filterRegistrationBean;
    }
}
