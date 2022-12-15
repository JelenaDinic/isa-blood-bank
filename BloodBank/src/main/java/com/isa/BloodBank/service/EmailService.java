package com.isa.BloodBank.service;

import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.VerifiactionToken;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;

@Service
public class EmailService {

    private final VerificationTokenService verificationTokenService;

    @Autowired
    public EmailService(VerificationTokenService verificationTokenService){
        this.verificationTokenService = verificationTokenService;
    }


    public void sendHtmlMail(RegisteredUser user) throws MessagingException{
        VerifiactionToken verifiactionToken = verificationTokenService.findbyUser(user);

        if(verifiactionToken != null){
            String token = verifiactionToken.getToken();
            Context context = new Context();
            context.setVariable("title", "Verify your email address");
            context.setVariable("link", "http://localhost:8082/activation?token"+token);
        }
    }
}
