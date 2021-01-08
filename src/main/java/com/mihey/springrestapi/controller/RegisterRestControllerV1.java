package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.dto.UserDTO;
import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.service.Impl.CodeServiceImpl;
import com.mihey.springrestapi.service.Impl.TwilioVerification;
import com.mihey.springrestapi.service.Impl.UserServiceImpl;
import com.mihey.springrestapi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RegisterRestControllerV1 {

    private final PasswordEncoder passwordEncoder;
    private final CodeServiceImpl codeService;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final TwilioVerification twilioVerification;

    @Autowired
    public RegisterRestControllerV1(PasswordEncoder passwordEncoder, CodeServiceImpl codeService, UserServiceImpl userService,
                                    UserMapper userMapper, TwilioVerification twilioVerification) {
        this.passwordEncoder = passwordEncoder;
        this.codeService = codeService;
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
        if (u.isEmpty()) {
            return new ResponseEntity<>("User doesn't exists", HttpStatus.FORBIDDEN);
        }
        if (!user.getPhoneNumber().equals(u.get().getPhoneNumber())) {
            return new ResponseEntity<>("Wrong phone number!", HttpStatus.FORBIDDEN);
        }
        return twilioVerification.verify(u.get(), code);
    }

    @PostMapping("/resend_code")
    public ResponseEntity<?> resendCode(@RequestBody UserDTO userDto) {
        Optional<User> u = userService.findByUserName(userMapper.toEntity(userDto).getUsername());
        if (u.isEmpty()) {
            return new ResponseEntity<>("User doesn't exists", HttpStatus.FORBIDDEN);
        }
        if (!userDto.getPhoneNumber().equals(u.get().getPhoneNumber())) {
            return new ResponseEntity<>("Wrong phone number!", HttpStatus.FORBIDDEN);
        }
        if (u.get().getStatus().name().equals("ACTIVE")) {
            return new ResponseEntity<>("The phone number is already confirmed!", HttpStatus.OK);
        }
        long time = u.get().getCode().getCreated().getTime() + 60_000;
        if (time > System.currentTimeMillis()) {
            return new ResponseEntity<>("you can request the code after " + (time - System.currentTimeMillis()) / 1000 + " seconds", HttpStatus.FORBIDDEN);
        }
        User user = u.get();
        Code currentCode = user.getCode();
        Code newCode = new Code();
        currentCode.setCode(newCode.getCode());
        currentCode.setCreated(newCode.getCreated());
        codeService.updateCode(currentCode);
        twilioVerification.sendSms(u.get().getPhoneNumber(), currentCode.getCode());
        return new ResponseEntity<>("Code sent!", HttpStatus.OK);
    }
}
