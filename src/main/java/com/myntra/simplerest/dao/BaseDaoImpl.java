package com.myntra.simplerest.dao;

import com.myntra.simplerest.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Abhinav on 22/06/17.
 */
public class BaseDaoImpl {
    @PersistenceContext
    EntityManager entityManager;

    public UserEntity create(UserEntity entity){
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }
}
