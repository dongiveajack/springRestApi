package com.myntra.simplerest.manager;

import com.myntra.simplerest.entity.UserEntity;
import com.myntra.simplerest.model.User;
import com.myntra.simplerest.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * Created by Abhinav on 22/06/17.
 */
@AllArgsConstructor
public class UserManager {
    private UserRepository repository;

    public User create(User user) throws Exception {
        UserEntity entity = convertToEntity(user, null);
        entity = repository.save(entity);
        return convertToEntry(entity);
    }

    public User update(Long id, User user) throws Exception {
        UserEntity entity = repository.findOne(id);
        entity = convertToEntity(user, entity);
        return convertToEntry(entity);
    }

    public User findById(Long id) throws Exception {
        UserEntity entity = repository.findOne(id);
        if (Objects.isNull(entity)) {
            throw new Exception("No user found for Id " + id);
        }
        return convertToEntry(entity);
    }

    private User convertToEntry(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        return user;
    }

    private UserEntity convertToEntity(User user, UserEntity entity) {
        if (Objects.isNull(entity)) {
            entity = new UserEntity();
        }
        entity.setName(user.getName());
        entity.setId(user.getId());
        return entity;
    }
}
