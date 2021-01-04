package com.mihey.springrestapi.dto;

import com.mihey.springrestapi.model.PostStatus;
import com.mihey.springrestapi.model.Writer;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PostDTO {
    private Integer id;
    private String content;
    private Timestamp created = new Timestamp(System.currentTimeMillis());
    private Timestamp updated = new Timestamp(System.currentTimeMillis());
    private PostStatus postStatus = PostStatus.UNDER_REVIEW;
    private Writer writer;

}
