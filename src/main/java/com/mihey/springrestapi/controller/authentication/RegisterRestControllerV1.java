package com.mihey.springrestapi.controller.authentication;

import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.service.TwilioVerification;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RegisterRestControllerV1 {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final TwilioVerification twilioVerification;

    @Autowired
    public RegisterRestControllerV1(PasswordEncoder passwordEncoder, UserServiceImpl userService,
                                    UserMapper userMapper, TwilioVerification twilioVerification) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userMapper = userMapper;
        this.twilioVerification = twilioVerification;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO user) {
        if (userService.findByUserName(userMapper.toEntity(user).getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userMapper.toEntity(user);
        Code code = new Code();
        u.setCode(code);
        code.setUser(u);
        userService.saveUser(u);
        twilioVerification.sendSms(user.getPhoneNumber(), code);
        return new ResponseEntity<>(userMapper.toDto(u), HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String code, @RequestBody UserDTO user) {
        User u = userService.findByUserName(userMapper.toEntity(user).getUsername()).orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));

        return new ResponseEntity<>("Invalid code", HttpStatus.BAD_REQUEST);
    }
}
