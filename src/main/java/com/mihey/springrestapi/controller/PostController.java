package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.PostStatus;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.PostRepository;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WriterRepository writerRepository;

    @PostMapping("/posts")
    public Post addPost(@Valid @RequestBody Post post, Principal user) {
        Writer w = writerRepository.findById(userRepository.findByUserName(user.getName()).get().getId()).get();
        post.setWriter(w);
//        post.setCreated(new Timestamp(System.currentTimeMillis()));
//        post.setUpdated(new Timestamp(System.currentTimeMillis()));
//        if (post.getStatus()==null) {
//            post.setStatus(PostStatus.UNDER_REVIEW);
//        }
        return postRepository.save(post);
    }
}