package com.okanciftci.cukatify.entities.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Rating")
@Getter
@Setter
public class Rating {

    private String userId;

    private String postId;

    private float rating;

    @Id
    private String id;

}
