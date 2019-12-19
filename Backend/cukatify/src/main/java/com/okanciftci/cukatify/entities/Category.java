package com.okanciftci.cukatify.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document("Category")
@Getter
@Setter
public class Category {

    @Id
    private String id;

    private String name;

    @DBRef
    @Indexed // Indeksliyoruz Select durumu hızlı çalışırken diğer operasyonlar daha yavaş ama
    @JsonIgnoreProperties("category")
    private List<Post> posts;

    public Category(String name) {
        super();
        this.name = name;
    }


}