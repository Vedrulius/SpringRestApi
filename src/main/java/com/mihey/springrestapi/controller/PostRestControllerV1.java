package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.PostStatus;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.service.PostServiceImpl;
import com.mihey.springrestapi.service.UserService;
import com.mihey.springrestapi.service.WriterServiceImpl;
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

    @Autowired
    public PostRestControllerV1(WriterServiceImpl writerService, PostServiceImpl postService,
                                UserService userRepository) {
        this.writerService = writerService;
        this.postService = postService;
        this.userService = userRepository;
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@Valid @RequestBody Post post, Principal user) {
        Writer writer = writerService.getWriterById(userService.findByUserName(user.getName()).get().getId());
        post.setWriter(writer);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
        if (post.getStatus() == null) {
            post.setStatus(PostStatus.UNDER_REVIEW);
        }
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //TODO edit method
    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestBody Post post, Principal user) {
        Writer writer = writerService.getWriterById(userService.findByUserName(user.getName()).get().getId());
        post.setWriter(writer);
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
        Post p = postService.updatePost(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable int id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }
}