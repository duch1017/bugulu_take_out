package com.itheima.reggie.filter;

/**
 * 检查用户是否已经完成登录
 */

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BeasContext;
import com.itheima.reggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取本次请求的URI
        String requestURI = request.getRequestURI();

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "employee/logout",
                "/backend/**",
                "/front/**"
        };

        //检查此次请求是否需要处理
        boolean check = check(urls, requestURI);

        //不需要处理，直接放行
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //判断登陆状态
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录，id为：{}", request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BeasContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");
        //未登录返回未登录结果
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));

    }

    /**
     * 路径匹配
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
