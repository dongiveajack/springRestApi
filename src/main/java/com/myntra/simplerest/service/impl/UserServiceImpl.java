package com.myntra.simplerest.service.impl;

import com.myntra.simplerest.repository.UserRepository;
import com.myntra.simplerest.entity.UserEntity;
import com.myntra.simplerest.model.User;
import com.myntra.simplerest.service.UserService;
import com.myntra.simplerest.utils.RabbitMsgPublisher;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static List<User> usersList = new ArrayList<>();
    private static Integer userId = 0;
    private RabbitMsgPublisher rabbitMsgPublisher;
    private UserRepository userRepository;
    private static final String EXCHANGE = "abhinavExchange";
    private static final String ROUTING_KEY = "abhinavQueue";
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User create(User user) {
        LOG.info("creating user : " + user.toString());
        user.setId(userId++);
        usersList.add(user);
        Message message = MessageBuilder.withBody(SerializationUtils.serialize(user))
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("bar", "baz")
                .setContentEncoding("string")
                .build();
        rabbitMsgPublisher.pushMsgToQueue(EXCHANGE, ROUTING_KEY, user);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userRepository.save(userEntity);
        return user;
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
    public List<UserEntity> findAllFromDb() {
        LOG.info("Fetching all info from DB : ");
        return userRepository.findByName("abhinav");
    }


}
