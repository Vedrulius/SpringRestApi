package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.dto.PostDTO;
import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.service.PostServiceImpl;
import com.mihey.springrestapi.service.UserService;
import com.mihey.springrestapi.service.WriterServiceImpl;
import com.mihey.springrestapi.service.mapper.PostMapper;
import com.mihey.springrestapi.service.mapper.WriterMapper;
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

    private final WriterServiceImpl writerService;
    private final PostServiceImpl postService;
    private final UserService userService;
    private final PostMapper postMapper;
    private final WriterMapper writerMapper;

    @Autowired
    public PostRestControllerV1(WriterServiceImpl writerService, PostServiceImpl postService,
                                UserService userRepository, PostMapper postMapper, WriterMapper writerMapper) {
        this.writerService = writerService;
        this.postService = postService;
        this.userService = userRepository;
        this.postMapper = postMapper;
        this.writerMapper = writerMapper;
    }

    @PostMapping
    public ResponseEntity<PostDTO> addPost(@Valid @RequestBody PostDTO post, Principal user) {
        Writer writer = writerMapper.toEntity(writerService.getWriterById(
                userService.findByUserName(user.getName()).get().getId()));

        Post p = postMapper.toEntity(post);
        p.setWriter(writer);
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

    @PutMapping
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO post, Principal user) {
        Writer writer = writerMapper.toEntity(writerService.getWriterById(
                userService.findByUserName(user.getName()).get().getId()));
        Post p = postMapper.toEntity(post);
        p.setWriter(writer);
        p.setUpdated(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(postService.updatePost(postMapper.toDto(p)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable int id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }
}