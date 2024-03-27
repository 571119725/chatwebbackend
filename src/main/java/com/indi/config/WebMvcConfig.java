package com.indi.config;

import com.indi.Interceptor.TokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    TokenAdminInterceptor tokenAdminInterceptor;
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenAdminInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/login");
    }
}
