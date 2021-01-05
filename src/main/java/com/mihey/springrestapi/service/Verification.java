package com.mihey.springrestapi.service;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Verification implements VerificationService{

    @Value("${accountSid}")
    private String ACCOUNT_SID;
    @Value("${authToken}")
    private String AUTH_TOKEN;
    @Value("${number}")
    private String NUMBER;

    @Autowired
    public Verification() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
}
