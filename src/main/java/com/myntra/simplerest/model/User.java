package com.myntra.simplerest.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonRootName(value = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String name;
}
