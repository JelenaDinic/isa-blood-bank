package com.isa.BloodBank.controller;

import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.SystemAdmin;
import com.isa.BloodBank.service.RegisteredUserService;
import com.isa.BloodBank.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/system-admin")
public class SystemAdminController {
    private final SystemAdminService service;

    @Autowired
    public SystemAdminController(SystemAdminService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SystemAdmin>> getAll() {
        List<SystemAdmin> systemAdmins = service.findAll();
        return new ResponseEntity<>(systemAdmins, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SystemAdmin> create(@RequestBody SystemAdmin systemAdmin) {
        SystemAdmin newSystemAdmin = service.create(systemAdmin);
        return new ResponseEntity<>(newSystemAdmin, HttpStatus.CREATED);
    }
}
