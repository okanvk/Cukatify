package com.okanciftci.cukatify.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Post")
public class Post {

    @Id
    public String id;

    public String text;

    public Post() {}

    public Post(String text) {
        this.text = text;

    }

    @Override
    public String toString() {
        return String.format(
                "Post[id=%s, text='%s']",
                id, text);
    }

}