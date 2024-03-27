package com.indi.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DirectRabbitConfig {
    @Bean
    public Queue SqlDirectQueue(){
        return new Queue("SqlQueue", true);
    }
    @Bean
    public DirectExchange DirectExchange() {
        return new DirectExchange("DirectExchange");
    }
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(SqlDirectQueue()).to(DirectExchange()).with("SqlQueue");
    }
}
