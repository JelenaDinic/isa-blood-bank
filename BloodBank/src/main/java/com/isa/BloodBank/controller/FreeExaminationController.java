package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.FreeExaminationDTO;
import com.isa.BloodBank.model.FreeExamination;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.service.FreeExaminationService;
import com.isa.BloodBank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="api/free-examination")
public class FreeExaminationController {
    private FreeExaminationService service;
    private StaffService staffService;

    @Autowired
    public FreeExaminationController(FreeExaminationService freeExaminationService, StaffService staffService) {
        this.service = freeExaminationService;
        this.staffService = staffService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody FreeExaminationDTO freeExaminationDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error creating new free examination!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            Staff staff = staffService.getStaffById(freeExaminationDTO.getStaffId());
            FreeExamination freeExamination = new FreeExamination(freeExaminationDTO, staff);
            service.create(freeExamination);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
