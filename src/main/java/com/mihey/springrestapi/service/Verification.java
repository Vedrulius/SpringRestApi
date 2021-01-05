package com.mihey.springrestapi.service;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Verification implements VerificationService {


    private final String ACCOUNT_SID;

    private final String AUTH_TOKEN;

    private final String NUMBER;

    public Verification(@Value("${accountSid}") String ACCOUNT_SID, @Value("${authToken}") String AUTH_TOKEN,
                        @Value("${number}") String NUMBER) {
        this.ACCOUNT_SID = ACCOUNT_SID;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.NUMBER = NUMBER;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
}
