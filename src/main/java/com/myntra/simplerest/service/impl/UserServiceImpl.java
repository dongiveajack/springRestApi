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
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static List<User> usersList = new ArrayList<>();
    private static Integer userId = 0;
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
            rabbitMsgPublisher.pushMsgToQueue(EXCHANGE, ROUTING_KEY, user);
            LOG.info("User created successfully!!");
        } catch (Exception e) {
            LOG.error("Error Ocured while creating user", e);
            builder.status(Response.Status.INTERNAL_SERVER_ERROR);
            builder.entity(user);
            return builder.build();
        }
        builder.status(Response.Status.CREATED);
        builder.entity(user);
        return builder.build();
    }

    @Override
    public User update(Integer id, User user) {
        usersList.get(id).setName(user.getName());
        return usersList.get(id);
    }

    @Override
    public User get(Integer id) {
        return usersList.get(id);
    }

    @Override
    public List<User> getAll() {
        return usersList;
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
