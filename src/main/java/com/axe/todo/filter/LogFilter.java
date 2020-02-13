package com.axe.todo.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Order(1)
//@WebFilter(filterName = "logFilter", urlPatterns = "/*", initParams = {
//        @WebInitParam(name = "URL", value = "http://localhost:8080")})
public class LogFilter implements Filter {
    String NO_LOGIN = "您还未登录";
    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/login", "/", "/register"};
    private String url;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.url = filterConfig.getInitParameter("URL");
        System.out.println("我是过滤器的初始化方法！URL=" + this.url + "变身！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("filter url:" + uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);


        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if (session != null && session.getAttribute("user") != null) {

                System.out.println("user:" + session.getAttribute("user"));
                filterChain.doFilter(request, response);
            } else {
                System.out.println("未登录");
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if (requestType != null && "XMLHttpRequest".equals(requestType)) {
                    response.getWriter().write(this.NO_LOGIN);
                } else {
                    System.out.println("返回登陆界面");
                    response.sendRedirect(request.getContextPath());
                }
                return;
            }
        }
    }

    /**
     * @param uri
     * @Author: xxxxx
     * @Description: 是否需要过滤
     * @Date: 2018-03-12 13:20:54
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            System.out.println(includeUrl + "," + uri
            );
            if (includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }

}
