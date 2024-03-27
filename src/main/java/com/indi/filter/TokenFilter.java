package com.indi.filter;

import com.indi.utils.TokenUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String token = request.getHeader("Authorization");
        if(uri.contains("/chatroom")){
            filterChain.doFilter(request, servletResponse);
        }
        if(uri.contains("/user") && ("OPTIONS".equals(method) || "POST".equals(method))){
            filterChain.doFilter(request, servletResponse);
        }else{
            if(!TokenUtils.checkToken(token))
                return;
            filterChain.doFilter(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
