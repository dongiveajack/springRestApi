package com.myntra.simplerest.utils;

/**
 * Created by Abhinav on 21/06/17.
 */
public interface MessageListener<T> {

    //public void processMessage(org.springframework.messaging.Message message) throws Exception;

    public void doProcess(T message) throws Exception;
}
