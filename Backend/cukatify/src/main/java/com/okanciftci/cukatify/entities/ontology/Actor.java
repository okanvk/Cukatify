package com.okanciftci.cukatify.entities.ontology;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Actor")
@Getter
@Setter
@NoArgsConstructor
public class Actor implements Serializable {


    @Id
    private String id;
    private String name;

    public Actor(String id, String name) {
        this.id = id;
        this.name = name;
    }
}