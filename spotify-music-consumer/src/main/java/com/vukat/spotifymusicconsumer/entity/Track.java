package com.vukat.spotifymusicconsumer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Track {

    private String link;
    private String name;
    private String uri;
    private String id;
    private String artistName;
    private String albumImg;
    private String lyrics;
    private String lyricsUrl;
    private String definition;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;


    void initDate(){
        this.createdAt = LocalDateTime.now();
    }

}
