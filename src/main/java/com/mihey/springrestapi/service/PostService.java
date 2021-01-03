package com.mihey.springrestapi.service;

import com.mihey.springrestapi.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getPosts();

    PostDTO getPostById(Integer id);

    PostDTO savePost(PostDTO post);

    PostDTO updatePost(PostDTO post);

    void deletePostById(Integer id);
}

