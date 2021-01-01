package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.model.dto.UserDTO;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userMapper.toDto(userRepository.findById(id).get());
    }

    @Override
    public UserDTO saveUser(UserDTO user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
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
