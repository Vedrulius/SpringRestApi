package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.service.UserServiceImpl;
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
public class UserRestControllerV1 {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/v1/register")
    public User addUser(@Valid @RequestBody User user) {
        if (userService.findByUserName(user.getUserName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus(Status.ACTIVE);
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return userService.saveUser(user);
    }

    @GetMapping("/api/v1/users")
    public List<User> getUsers() {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<User> getRegionById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/v1/users")
    public ResponseEntity<User> updateRegion(@RequestBody User user) {
        User u = userService.saveUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
