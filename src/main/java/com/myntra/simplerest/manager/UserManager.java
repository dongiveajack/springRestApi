package com.myntra.simplerest.manager;

import com.myntra.simplerest.entity.UserEntity;
import com.myntra.simplerest.model.User;
import com.myntra.simplerest.repository.UserRepository;
import lombok.AllArgsConstructor;

/**
 * Created by Abhinav on 22/06/17.
 */
@AllArgsConstructor
public class UserManager {
    private UserRepository repository;

    public User create(User user) throws Exception {
        UserEntity entity = convertToEntity(user);
        entity = repository.save(entity);
        return convertToEntry(entity);
    }

    private User convertToEntry(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        return user;
    }

    private UserEntity convertToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setId(user.getId());
        return entity;
    }
}
