package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.service.CodeService;
import com.mihey.springrestapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TwilioVerificationTest {

    @Autowired
    private TwilioVerification twilioVerification;
    @MockBean
    private UserService userService;
    @MockBean
    private CodeService codeService;

    @Test
    void sendSms() {
    }

    @Test
    void verify() {
    }
}