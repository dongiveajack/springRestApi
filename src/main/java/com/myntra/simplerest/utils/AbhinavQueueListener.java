package com.myntra.simplerest.utils;

import com.myntra.simplerest.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Abhinav on 21/06/17.
 */
public class AbhinavQueueListener extends AbstractRabbitMsgListener<User> {
    private static final Logger LOG = LoggerFactory.getLogger(AbhinavQueueListener.class);

    @Override
    public void doProcess(User message) throws Exception {
        LOG.info("Message Read From AbhinavQueue : ", message.toString());
    }
}
