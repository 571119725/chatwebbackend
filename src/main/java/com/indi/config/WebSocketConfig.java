package com.indi.config;

import com.indi.mq.DirectPublisher;
import com.indi.utils.RedisUtils;
import com.indi.ws.ChatEndPoint;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        ChatEndPoint.redisUtils = redisUtils;
    }
    @Autowired
    public void setDirectPublisher(DirectPublisher directPublisher){ChatEndPoint.directPublisher = directPublisher;}
}
