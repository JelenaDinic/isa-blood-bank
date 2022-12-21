package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping(path="api/staff")
public class StaffController {

    private final StaffService service;

    @Autowired
    public StaffController(StaffService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<Staff>> getAll() {
        List<Staff> staff = service.findAll();
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/all-bloodbank-staff/{id}")
    public ResponseEntity<List<Staff>> getAllByCenterId(@PathVariable("id") int id) {
        List<Staff> staff = service.findAllByCenterId(id);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody StaffCreationDTO staffCreationDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error creating new staff!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            service.create(staffCreationDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @PathVariable int id, @RequestBody StaffCreationDTO staffDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error updating staff!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            service.update(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/{id}")
    public StaffCreationDTO getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

}
