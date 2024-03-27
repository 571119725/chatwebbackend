package com.indi.ws;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indi.domain.Message;
import com.indi.domain.ResultMessage;
import com.indi.mq.DirectPublisher;
import com.indi.utils.MessageUtils;
import com.indi.utils.RedisUtils;
import com.indi.utils.TokenUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chatroom/{token}", configurator = GetHttpSessionConfigurator.class)
public class ChatEndPoint {
    private static Map<String, ChatEndPoint> onlineUser = new ConcurrentHashMap<>();
    private String username;
    private Session session;
    public static RedisUtils redisUtils;
    public static DirectPublisher directPublisher;
    private void broadcastAllUsers(String message) {
        try {
            Set<String> names = onlineUser.keySet();
            for (String name : names) {
                onlineUser.get(name).session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void broadcastOthers(String message, String username) {
        try {
            Set<String> names = onlineUser.keySet();
            for (String name : names) {
                if(!username.equals(name)){
                    onlineUser.get(name).session.getBasicRemote().sendText(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig, @PathParam("token") String token) {
        if (token != null && TokenUtils.checkToken(token)) {
            this.session = session;
            this.username = TokenUtils.getUserName(token);
            onlineUser.put(this.username, this);

            String message = MessageUtils.generateMessage(MessageUtils.ONLINE, onlineUser.keySet());
            broadcastAllUsers(message);

            String historyMessage = MessageUtils.generateMessage(MessageUtils.HISTORY, redisUtils.getHistoryMessageJson());
            try {
                Session temp = onlineUser.get(this.username).session;
                if(temp != null) temp.getBasicRemote().sendText(historyMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if(!message.equals("ping")){
            broadcastOthers(message, this.username);
            redisUtils.addMessage(message);
            directPublisher.sendMessage(message);
        }else{
            try {
                Session temp = onlineUser.get(this.username).session;
                if(temp != null) temp.getBasicRemote().sendText("pong");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @OnClose
    public void onClose(Session session) {
        onlineUser.remove(this.username);
        broadcastAllUsers(MessageUtils.generateMessage(MessageUtils.ONLINE, onlineUser.keySet()));
    }
    @OnError
    public void onError(Session session, Throwable t){
    }
}
