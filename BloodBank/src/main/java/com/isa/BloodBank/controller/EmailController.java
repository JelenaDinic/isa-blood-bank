package com.isa.BloodBank.controller;
import com.isa.BloodBank.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/email")
public class EmailController {

    public final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("/get-link")
    public String create(String link) {
        return emailService.getLinkFromHtml(link);
    }
}
