package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.service.RegisteredUserService;
import com.isa.BloodBank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/staff")
public class StaffController {

    private final StaffService service;

    @Autowired
    public StaffController(StaffService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAll() {
        List<Staff> staff = service.findAll();
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Staff> create(@RequestBody StaffCreationDTO staffCreationDTO) {
        Staff staff = service.create(staffCreationDTO);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

}
