package com.myntra.simplerest.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Objects;

/**
 * Created by Abhinav on 20/06/17.
 */
public class RabbitMsgPublisher {
    private RabbitTemplate rabbitTemplate;

    public RabbitMsgPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pushMsgToQueue(String exchange, String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
