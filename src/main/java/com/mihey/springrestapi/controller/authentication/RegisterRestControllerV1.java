package com.mihey.springrestapi.controller.authentication;

import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.model.Status;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
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

    private static final String ACCOUNT_SID = "AC108504357e43517c2dca0384798f49fc";
    private static final String AUTH_TOKEN = "944a418a111b75e5aae988d3dec506cd";
    private static final String VERIFICATION_SID = "VAbc26608ae1f68f0d9c97c2ad26fae9e5";

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public RegisterRestControllerV1(PasswordEncoder passwordEncoder, UserServiceImpl userService, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO user) {
        if (userService.findByUserName(userMapper.toEntity(user).getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator(VERIFICATION_SID, user.getPhoneNumber(), "sms").create();
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String code, @RequestBody UserDTO user) {
        User u = userService.findByUserName(userMapper.toEntity(user).getUsername()).orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));
        VerificationCheck verificationCheck = VerificationCheck.creator(VERIFICATION_SID, code)
                .setTo(user.getPhoneNumber()).create();
        if (verificationCheck.getStatus().equals("approved")) {
            u.setStatus(Status.ACTIVE);
            return new ResponseEntity<>(userService.updateUser(userMapper.toDto(u)), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid code", HttpStatus.BAD_REQUEST);
    }
}
