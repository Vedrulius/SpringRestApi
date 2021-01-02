package com.mihey.springrestapi.model.dto;

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
    private Timestamp updated;
    private Writer writer;
    private PostStatus postStatus;

}
