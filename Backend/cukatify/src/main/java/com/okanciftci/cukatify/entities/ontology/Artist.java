package com.okanciftci.cukatify.entities.ontology;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Artist")
@Getter
@Setter
@NoArgsConstructor
public class Artist implements Serializable {


    @Id
    private String id;
    private String name;
    private String page;
    private String description;
    private String imageUrl;

    public Artist(String id, String name, String page,String description,String imageUrl) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}