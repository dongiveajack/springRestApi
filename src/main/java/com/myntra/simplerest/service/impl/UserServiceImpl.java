package com.myntra.simplerest.service.impl;

import com.myntra.simplerest.manager.UserManager;
import com.myntra.simplerest.repository.UserRepository;
import com.myntra.simplerest.entity.UserEntity;
import com.myntra.simplerest.model.User;
import com.myntra.simplerest.service.UserService;
import com.myntra.simplerest.utils.RabbitMsgPublisher;
import lombok.AllArgsConstructor;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.apache.cxf.jaxrs.impl.ResponseImpl;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static List<User> usersList = new ArrayList<>();
    private RabbitMsgPublisher rabbitMsgPublisher;
    private UserRepository userRepository;
    private UserManager manager;
    private static final String EXCHANGE = "abhinavExchange";
    private static final String ROUTING_KEY = "abhinavQueue";
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Response create(User user) {
        LOG.info("creating user : " + user.toString());
        Response.ResponseBuilder builder = new ResponseBuilderImpl();
        try {
            manager.create(user);
            builder.status(Response.Status.CREATED);
            rabbitMsgPublisher.pushMsgToQueue(EXCHANGE, ROUTING_KEY, user);
            LOG.info("User created successfully!!");
        } catch (Exception e) {
            LOG.error("Error Occurred while creating user", e);
            builder.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        builder.entity(user);
        return builder.build();
    }

    @Override
    public Response update(Long id, User user) {
        LOG.info("Updating User : " + user.toString());
        Response.ResponseBuilder builder = new ResponseBuilderImpl();
        try {
            manager.update(id, user);
            builder.status(Response.Status.OK);
            String successMsg = MessageFormat.format("User {0} Updated Successfully ", id);
            LOG.info(successMsg);
        } catch (Exception e) {
            LOG.error("Error Occurred while Updating User", e);
            builder.status(Response.Status.INTERNAL_SERVER_ERROR);
            builder.tag(e.getMessage());
        }
        builder.entity(user);
        return builder.build();
    }

    @Override
    public Response get(Long id) {
        Response.ResponseBuilder builder = new ResponseBuilderImpl();
        try {
            User user = manager.findById(id);
            builder.entity(user);
            builder.status(Response.Status.FOUND);
            String successMsg = MessageFormat.format("User {0} Found : {1}", id, user.toString());
            LOG.info(successMsg);
        } catch (Exception e) {
            LOG.error("Error Occurred while fetching User", e);
            builder.status(Response.Status.NO_CONTENT);
            builder.tag(e.getMessage());
        }
        return builder.build();
    }

    @Override
    public Response getAll() {
        Response.ResponseBuilder builder = new ResponseBuilderImpl();
            List<User> userList = manager.findAll();
            builder.status(Response.Status.FOUND);
            builder.entity(userList);
            LOG.info("All Users Fetched from Db");
        return builder.build();
    }

    @Override
    public void delete(Integer id) {
        usersList.remove((int) id);
    }

    @Override
    public List<UserEntity> findAll() {
        LOG.info("Fetching all info from DB : ");
        return userRepository.findByName("abhinav");
    }


}
