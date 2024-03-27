package com.indi.ws;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestListener implements ServletRequestListener {
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ((HttpServletRequest) servletRequestEvent.getServletRequest()).getSession();
    }
}
