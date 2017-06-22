package com.myntra.simplerest.utils;

import org.springframework.messaging.Message;

/**
 * Created by Abhinav on 21/06/17.
 */
public abstract class AbstractRabbitMsgListener<T> implements MessageListener<T> {

    /*@Override
    public void processMessage(T message) throws Exception {
        doProcess(message);
    }
*/
    public abstract void doProcess(T message) throws Exception;
}
