package com.isa.BloodBank.service;

import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository repository) {
        this.repository = repository;
    }

    public RegisteredUser create(RegisteredUser registeredUser) {
        return repository.save(registeredUser);
    }

    public List<RegisteredUser> findAll() {
        return repository.findAll();
    }
}
