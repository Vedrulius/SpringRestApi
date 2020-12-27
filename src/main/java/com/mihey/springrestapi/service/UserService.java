package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(Integer id);

    User saveUser(User region);

    User updateUser(User region);

    void deleteUserById(Integer id);


}
