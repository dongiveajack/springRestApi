package com.myntra.simplerest.dao;

import com.myntra.simplerest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Abhinav on 22/06/17.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
}
