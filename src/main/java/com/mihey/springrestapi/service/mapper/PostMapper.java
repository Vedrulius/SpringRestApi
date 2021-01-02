package com.mihey.springrestapi.service.mapper;

import com.mihey.springrestapi.model.Post;
import com.mihey.springrestapi.model.dto.PostDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper extends EntityMapper<Post, PostDTO> {
    Post toEntity(PostDTO postDTO);

    PostDTO toDto(Post post);

    List<Post> toEntity(List<PostDTO> postDTOs);

    List<PostDTO> toDto(List<Post> posts);
}

