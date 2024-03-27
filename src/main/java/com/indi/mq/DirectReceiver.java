package com.indi.mq;

import com.indi.domain.Message;
import com.indi.service.MessageService;
import com.indi.utils.MQUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = MQUtils.SqlQueue),
        exchange = @Exchange(value = MQUtils.DirectExchange),
        key = MQUtils.SqlKey
))
public class DirectReceiver {
    @Autowired
    MessageService messageService;
    @RabbitHandler
    public void process(Message message) {
        messageService.insertMessage(message.getToName(), message);
    }
}
