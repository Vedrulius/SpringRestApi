package com.mihey.springrestapi.repository;

import com.mihey.springrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
