package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    User getUserById(Integer id);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Integer id);

    Optional<User> findByUserName(String username);
}
