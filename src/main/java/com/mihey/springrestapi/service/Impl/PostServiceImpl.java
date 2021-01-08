package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.repository.PostRepository;
import com.mihey.springrestapi.service.PostService;
import com.mihey.springrestapi.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer id) {

        return postRepository.findById(id).get();
    }

    @Override
    public Post savePost(Post post) {

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {

        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Integer id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
    }
}
