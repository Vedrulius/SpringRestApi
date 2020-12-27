package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.repository.PostRepository;
import com.mihey.springrestapi.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post savePost(Post region) {
        return postRepository.save(region);
    }

    @Override
    public Post updatePost(Post region) {
        return postRepository.save(region);
    }

    @Override
    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }
}
