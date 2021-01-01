package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.PostStatus;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.model.dto.PostDTO;
import com.mihey.springrestapi.service.PostServiceImpl;
import com.mihey.springrestapi.service.UserService;
import com.mihey.springrestapi.service.WriterServiceImpl;
import com.mihey.springrestapi.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestControllerV1 {

    private WriterServiceImpl writerService;
    private PostServiceImpl postService;
    private UserService userService;
    private PostMapper postMapper;

    @Autowired
    public PostRestControllerV1(WriterServiceImpl writerService, PostServiceImpl postService,
                                UserService userRepository, PostMapper postMapper) {
        this.writerService = writerService;
        this.postService = postService;
        this.userService = userRepository;
        this.postMapper = postMapper;
    }

    @PostMapping //TODO edit method
    public ResponseEntity<PostDTO> addPost(@Valid @RequestBody PostDTO post, Principal user) {
//        Writer writer = writerService.getWriterById(userService.findByUserName(user.getName()).get().getId());
//        post.setWriter(writer);
        Post p = postMapper.toEntity(post);
        p.setCreated(new Timestamp(System.currentTimeMillis()));
        p.setUpdated(new Timestamp(System.currentTimeMillis()));
        if (p.getStatus() == null) {
            p.setStatus(PostStatus.UNDER_REVIEW);
        }
        return new ResponseEntity<>(postService.savePost(postMapper.toDto(p)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id) {
        PostDTO post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //TODO edit method
    @PutMapping
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO post, Principal user) {
//        Writer writer = writerService.getWriterById(userService.findByUserName(user.getName()).get().getId());
        Post p = postMapper.toEntity(post);
//        post.setWriter(writer);
        p.setUpdated(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(postService.updatePost(postMapper.toDto(p)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable int id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }
}