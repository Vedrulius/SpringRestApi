package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.service.Impl.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public UserRestControllerV1(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }



    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userMapper.toDto(userService.getUsers()), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO user = userMapper.toDto(userService.getUserById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        UserDTO u = userMapper.toDto(userService.updateUser(userMapper.toEntity(user)));
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
