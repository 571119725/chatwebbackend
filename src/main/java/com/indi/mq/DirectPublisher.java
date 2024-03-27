package com.indi.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indi.domain.Message;
import com.indi.domain.ResultMessage;
import com.indi.utils.MQUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendMessage(String str){
        ObjectMapper mapper = new ObjectMapper();
        try{
            ResultMessage rm = mapper.readValue(str, ResultMessage.class);
            String message = mapper.writeValueAsString(rm.getMessage());
            Message mess = mapper.readValue(message, Message.class);
            rabbitTemplate.convertAndSend(MQUtils.DirectExchange, MQUtils.SqlKey, mess);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
