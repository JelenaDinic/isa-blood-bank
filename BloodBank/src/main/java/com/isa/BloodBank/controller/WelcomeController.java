package com.isa.BloodBank.controller;

import com.isa.BloodBank.model.AuthRequest;
import com.isa.BloodBank.Util.JwtUtil;
import com.isa.BloodBank.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            emailSenderService.sendSimpleEmail("vanjateodorovic00@gmail.com", "legendo", "ti si legenda");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
}
