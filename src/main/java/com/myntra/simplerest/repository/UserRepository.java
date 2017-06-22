package com.myntra.simplerest.repository;

import com.myntra.simplerest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Abhinav on 22/06/17.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
}
