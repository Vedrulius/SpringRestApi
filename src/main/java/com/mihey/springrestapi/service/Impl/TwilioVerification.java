package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Status;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.service.CodeService;
import com.mihey.springrestapi.service.UserService;
import com.mihey.springrestapi.service.VerificationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TwilioVerification implements VerificationService {

    private final UserService userService;
    private final CodeService codeService;
    private final String TWILIO_NUMBER;
    private final String VALIDATION_TIME;


    @Autowired
    public TwilioVerification(@Value("${accountSid}") String accountSid, @Value("${authToken}") String authToken,
                              UserService userService, CodeService codeService, @Value("${twilioNumber}") String twilioNumber, @Value("${validationTime}") String validationTime) {
        this.userService = userService;
        this.codeService = codeService;
        TWILIO_NUMBER = twilioNumber;
        VALIDATION_TIME = validationTime;
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String phoneNumber, String code) {
        MessageCreator creator = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER), code);
        creator.create();
    }

    public ResponseEntity<?> verify(User user, String code) {

        long time = System.currentTimeMillis() - user.getCode().getCreated().getTime();
        String verifCode = user.getCode().getCode();
        if (time > Long.parseLong(VALIDATION_TIME)) {
            return new ResponseEntity<>("Code is expired!", HttpStatus.BAD_REQUEST);
        }
        if (!java.util.Objects.equals(code, verifCode)) {
            return new ResponseEntity<>("Invalid code!", HttpStatus.BAD_REQUEST);
        } else {
            user.setStatus(Status.ACTIVE);
            userService.saveUser(user);
            user.getCode().setConfirmed(true);
            codeService.updateCode(user.getCode());
            return new ResponseEntity<>("Code verified successfully! User status is :" + user.getStatus(), HttpStatus.OK);
        }
    }
}
