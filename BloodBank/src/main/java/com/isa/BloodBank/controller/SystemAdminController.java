package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.PasswordChangeDTO;
import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.dto.SysadminCreationDTO;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.SystemAdmin;
import com.isa.BloodBank.service.RegisteredUserService;
import com.isa.BloodBank.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEMADMIN')")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody SysadminCreationDTO systemAdmin, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error creating new staff!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            service.create(systemAdmin);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEMADMIN')")
    @PostMapping(path = "/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        try {
            service.changePassword(passwordChangeDTO.getNewPassword(), passwordChangeDTO.getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
