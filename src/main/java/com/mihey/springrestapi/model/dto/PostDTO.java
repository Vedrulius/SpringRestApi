package com.mihey.springrestapi.model.dto;

import com.mihey.springrestapi.model.PostStatus;
import com.mihey.springrestapi.model.Writer;

import java.sql.Timestamp;

public class PostDTO {
    private Integer id;
    private String content;
    private Timestamp updated;
    private Writer writer;
    private PostStatus postStatus;

}
