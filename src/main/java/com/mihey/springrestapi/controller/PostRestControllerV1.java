package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.PostStatus;
import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.PostRepository;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.repository.WriterRepository;
import com.mihey.springrestapi.service.PostServiceImpl;
import com.mihey.springrestapi.service.RegionServiceImpl;
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
public class PostRestControllerV1 {

    @Autowired
    private WriterServiceImpl writerService;

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/api/v1/posts")
    public Post addPost(@Valid @RequestBody Post post, Principal user) {
        Writer w = writerService.getWriterById(userRepository.findByUserName(user.getName()).get().getId());
        post.setWriter(w);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
        if (post.getStatus()==null) {
            post.setStatus(PostStatus.UNDER_REVIEW);
        }
        return postService.savePost(post);
    }



    @GetMapping("/api/v1/posts")
    public ResponseEntity<List<Post>> getAllRegions() {
        List<Post> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<Post> getRegionById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @PutMapping("/api/v1/posts")
    public ResponseEntity<Post> updateRegion(@RequestBody Post post) {
        Post post1 = postService.savePost(post);
        return new ResponseEntity<>(post1, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }
}