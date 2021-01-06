package com.mihey.springrestapi.controller.authentication;

import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.service.TwilioVerification;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO user) {
        if (userService.findByUserName(userMapper.toEntity(user).getUsername()).isPresent()) {
            return new ResponseEntity<>("User exists", HttpStatus.FORBIDDEN);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userMapper.toEntity(user);
        Code code = new Code();
        u.setCode(code);
        code.setUser(u);
        userService.saveUser(u);
        twilioVerification.sendSms(user.getPhoneNumber(), code.getCode());
        return new ResponseEntity<>(userMapper.toDto(u), HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String code, @RequestBody UserDTO user) {
        Optional<User> u = userService.findByUserName(userMapper.toEntity(user).getUsername());
        if (u.isEmpty()){
            return new ResponseEntity<>("User doesn't exists", HttpStatus.FORBIDDEN);
        }
            return twilioVerification.verify(u.get(),code);
    }


}
