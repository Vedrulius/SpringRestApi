package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveUser(User region) {
        return userRepository.save(region);
    }

    @Override
    public User updateUser(User region) {
        return userRepository.save(region);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }


}
