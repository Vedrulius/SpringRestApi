package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getPosts();

    Post getPostById(Integer id);

    Post savePost(Post region);

    Post updatePost(Post post);

    void deletePostById(Integer id);
}

