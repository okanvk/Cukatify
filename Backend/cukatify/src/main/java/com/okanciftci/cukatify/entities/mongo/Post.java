package com.okanciftci.cukatify.entities.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.okanciftci.cukatify.entities.mongo.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Document("Post")
@Getter
@Setter
public class Post {

    @Id
    private String id;

    private String content;

    private String title;

    private String description;

    private Integer rating;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

    private boolean isApproved;

    private String fileName;

    @DBRef
    @JsonIgnoreProperties("posts")
    private Category category;


    public Post(String content, LocalDateTime createdAt, boolean isApproved,Category category,Integer rating,String title) {
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
        this.isApproved = isApproved;
        this.category = category;
        this.title = title;
    }

    public Post() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public void addCategory(Category category){
        this.setCategory(category);
    }


}