package com.mihey.springrestapi.service.mapper;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.dto.UserDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends EntityMapper<User, UserDTO> {
    User toEntity(UserDTO userDTO);

    UserDTO toDto(User user);

    List<User> toEntity(List<UserDTO> userDTOs);

    List<UserDTO> toDto(List<User> users);
}

