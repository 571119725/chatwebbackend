package com.indi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indi.domain.Message;
import com.indi.domain.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    public Set<String> getKeys(String prefix){
        return redisTemplate.keys(prefix.concat("_*"));
    }
    public Map<String, Set<String>> getHistoryMessageJson(){
        Set<String> keySet = getKeys("room");
        Map<String, Set<String>> result = new ConcurrentHashMap<>();
        for(String key : keySet){
            result.put(key, redisTemplate.opsForZSet().range(key, 0, -1));
        }
        return result;
    }

    public void addMessage(String data){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ResultMessage rm = mapper.readValue(data, ResultMessage.class);
            String message = mapper.writeValueAsString(rm.getMessage());
            Message mess = mapper.readValue(message, Message.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(mess.getTime());
            long timeStamp = date.getTime();
            String roomKey = mess.getToName();
            redisTemplate.boundZSetOps(roomKey).add(message, timeStamp);
            redisTemplate.expire(roomKey, 6, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
