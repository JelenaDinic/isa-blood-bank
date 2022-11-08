package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/registered-user")
public class RegisteredUserController {

    private final RegisteredUserService service;

    @Autowired
    public RegisteredUserController(RegisteredUserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RegisteredUser>> getAll() {
        List<RegisteredUser> registeredUsers = service.findAll();
        return new ResponseEntity<>(registeredUsers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegisteredUser> create(@RequestBody RegisteredUser registeredUser) {
        RegisteredUser newRegisteredUser = service.create(registeredUser);
        return new ResponseEntity<>(newRegisteredUser, HttpStatus.CREATED);
    }

    @GetMapping(path = "/allUsers")
    public ResponseEntity<List<UserDisplayDTO>> findAllUsers() {
        List<UserDisplayDTO> userDisplayDTOs = service.findAllUsers();
        return new ResponseEntity<>(userDisplayDTOs, HttpStatus.OK);
    }
}
