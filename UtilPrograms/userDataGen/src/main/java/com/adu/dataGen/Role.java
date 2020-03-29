package com.adu.dataGen;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Role")
@Getter
@Setter
public class Role {

    @Id
    private String id;

    private String name;

    private String description;

    @DBRef(lazy = true)
    private List<User> user_ids;

}