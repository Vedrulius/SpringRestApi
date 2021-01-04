package com.mihey.springrestapi.controller.authentication;

import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RegisterRestControllerV1 {

    private static final String ACCOUNT_SID="AC108504357e43517c2dca0384798f49fc";
    private static final String AUTH_TOKEN="866abfd2262f783622160f608d437637";
    private static final String VERIFICATION_SID="VAbc26608ae1f68f0d9c97c2ad26fae9e5";

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public RegisterRestControllerV1(PasswordEncoder passwordEncoder, UserServiceImpl userService, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    {
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO user) {
        if (userService.findByUserName(userMapper.toEntity(user).getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus(Status.CONFIRMATION_REQUIRED);
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }
}
