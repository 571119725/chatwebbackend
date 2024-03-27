package com.indi.filter;

import com.mysql.cj.util.StringUtils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Component
@WebFilter(filterName="CorsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {
    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    @Override
    public void init(FilterConfig filterConfig)  {
        allowOrigin = "*";
        allowMethods = "POST,GET,PUT,DELETE,OPTIONS";
        allowCredentials = "true";
        allowHeaders = "Content-Type,X-token,Authorization";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!StringUtils.isNullOrEmpty(allowOrigin)) {
            if (allowOrigin.equals("*")) {
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            }else {
                List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
                if (!allowOriginList.isEmpty()) {
                    String currenOrigin = request.getHeader("Origin");
                    if (allowOriginList.contains(currenOrigin)) {
                        response.setHeader("Access-Control-Allow-Origin", currenOrigin);
                    }
                }
            }
        }
        if (!StringUtils.isNullOrEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (!StringUtils.isNullOrEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (!StringUtils.isNullOrEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }

        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {
    }
}
