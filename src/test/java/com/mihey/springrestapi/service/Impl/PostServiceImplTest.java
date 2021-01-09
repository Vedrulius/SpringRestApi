package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.repository.PostRepository;
import com.mihey.springrestapi.repository.RegionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostServiceImpl postService;
    @MockBean
    private PostRepository postRepository;

    Post post = new Post();

    {
        post.setId(1);
        post.setContent("Hello JavaTest");
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUpdated(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void getPostsTest() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        Mockito.when(postRepository.findAll()).thenReturn(posts);
        assertEquals(posts, postService.getPosts());
        assertNotEquals(0, postService.getPosts().size());
    }

    @Test
    void getPostByIdTest() {
        Optional<Post> p = Optional.of(post);
        Mockito.when(postRepository.findById(anyInt())).thenReturn(p);
        assertEquals(post, postService.getPostById(1));
        assertNotEquals("Hello Java", postService.getPostById(1)
                .getContent());
    }

    @Test
    void savePostTest() {
        Mockito.when(postRepository.save(post)).thenReturn(post);
        assertEquals(post, postService.savePost(post));
        assertNotEquals("Hello Test", postService.savePost(post).getContent());
    }

    @Test
    void updatePostTest() {
        post.setContent("Hello, world!");
        Mockito.when(postRepository.save(post)).thenReturn(post);
        assertEquals(post, postService.savePost(post));
        assertEquals("Hello, world!", postService.savePost(post).getContent());
        assertNotEquals("Hello Test", postService.savePost(post).getContent());
    }

    @Test
    public void deletePostByIdTest() {
        Mockito.doNothing().when(postRepository).deleteById(anyInt());
        postService.deletePostById(1);
    }
}