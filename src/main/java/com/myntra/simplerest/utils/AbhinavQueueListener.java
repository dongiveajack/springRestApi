package com.myntra.simplerest.utils;

import com.myntra.simplerest.model.User;
import org.apache.log4j.Logger;


/**
 * Created by Abhinav on 21/06/17.
 */
public class AbhinavQueueListener {
    private static final Logger LOG = Logger.getLogger(AbhinavQueueListener.class);

    public void doProcess(User message) throws Exception {
        LOG.info("Message Read From AbhinavQueue : " + message.toString());
    }
}
