package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Code;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioVerification implements VerificationService {


    private final String ACCOUNT_SID;

    private final String AUTH_TOKEN;

    private final String TWILIO_NUMBER;

    @Autowired
    public TwilioVerification(@Value("${accountSid}") String ACCOUNT_SID, @Value("${authToken}") String AUTH_TOKEN,
                        @Value("${twilioNumber}") String TWILIO_NUMBER) {
        this.ACCOUNT_SID = ACCOUNT_SID;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.TWILIO_NUMBER = TWILIO_NUMBER;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSms(String phoneNumber, Code code){
        MessageCreator creator = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER), code.getCode());
        creator.create();
    }
}
