package com.indi.Interceptor;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.indi.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenAdminInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        String token = request.getHeader("Authorization");
//        try {
//            if(TokenUtils.checkToken(token))
//                return true;
//        } catch (Exception ex) {
//            response.setStatus(401);
//            return false;
//        }
//        return false;
        return true;
    }
}