package com.myntra.simplerest.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Abhinav on 22/06/17.
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String name;
}
