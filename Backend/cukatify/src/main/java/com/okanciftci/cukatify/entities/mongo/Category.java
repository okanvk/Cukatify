package com.okanciftci.cukatify.entities.mongo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document("Category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String id;
    private String name;

    @DBRef
    @JsonIgnoreProperties("category")
    private List<Post> posts;

    public Category(String name) {
        super();
        this.name = name;
    }

    public void addPost(Post post){
        this.getPosts().add(post);
    }


}