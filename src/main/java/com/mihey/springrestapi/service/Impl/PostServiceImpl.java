package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.dto.PostDTO;
import com.mihey.springrestapi.repository.PostRepository;
import com.mihey.springrestapi.service.PostService;
import com.mihey.springrestapi.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDTO> getPosts() {
        return postMapper.toDto(postRepository.findAll());
    }

    @Override
    public PostDTO getPostById(Integer id) {

        return postMapper.toDto(postRepository.findById(id).get());
    }

    @Override
    public PostDTO savePost(PostDTO post) {

        return postMapper.toDto(postRepository.save(postMapper.toEntity(post)));
    }

    @Override
    public PostDTO updatePost(PostDTO post) {

        return postMapper.toDto(postRepository.save(postMapper.toEntity(post)));
    }

    @Override
    public void deletePostById(Integer id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
    }
}
