package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getUsers();

    UserDTO getUserById(Integer id);

    UserDTO saveUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    void deleteUserById(Integer id);

    Optional<User> findByUserName(String username);
}
