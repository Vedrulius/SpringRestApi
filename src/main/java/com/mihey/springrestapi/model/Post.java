package com.mihey.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "content")
    private String content;
    @Column(name = "created")
    private Timestamp created = new Timestamp(System.currentTimeMillis());
    @Column(name = "updated")
    private Timestamp updated = new Timestamp(System.currentTimeMillis());
    @ManyToOne
    @JoinColumn(name = "writer_id")
    @JsonIgnore
    private Writer writer;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatus status = PostStatus.UNDER_REVIEW;

}
