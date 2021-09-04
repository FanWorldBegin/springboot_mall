package com.im.mall.filter;

import com.im.mall.common.Constant;
import com.im.mall.model.pojo.User;
import com.im.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  实现Filter 过滤器的接口
 *  用户校验过滤器
 */
public class UserFilter implements Filter {
    @Autowired
    UserService userService;

    public static User currentUser; // 暂不考虑多线程

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     *
     * @param servletRequest  - 可以拿到session
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 帮我们校验


        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();

        //判断用户是否登录
        currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if(currentUser == null) {
            // return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN); 返回的是void
            // 可替代的打印方法
            PrintWriter out  = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 10007,\n" +
                    "    \"msg\": \"NEED_LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            return;
        }
        // 继续走接下来
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
