package com.myntra.simplerest.utils;

/**
 * Created by Abhinav on 21/06/17.
 */
public interface MessageListener<T> {
    void doProcess(T message) throws Exception;
}
