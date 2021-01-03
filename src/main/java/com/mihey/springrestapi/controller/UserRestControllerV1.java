package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;
import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestControllerV1 {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public UserRestControllerV1(PasswordEncoder passwordEncoder, UserServiceImpl userService, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO user) {
        if (userService.findByUserName(userMapper.toEntity(user).getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus(Status.ACTIVE);
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        UserDTO u = userService.updateUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
