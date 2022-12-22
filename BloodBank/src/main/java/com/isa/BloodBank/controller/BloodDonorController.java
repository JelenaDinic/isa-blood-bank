package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.BloodDonorCreationDTO;
import com.isa.BloodBank.model.BloodDonorForm;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.service.BloodDonorService;
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
@CrossOrigin(origins = "http://locahost:4200")
@RequestMapping(path="api/blood-donor")
public class BloodDonorController {

    private BloodDonorService bloodDonorService;

    public BloodDonorController(BloodDonorService bloodDonorService){
        this.bloodDonorService = bloodDonorService;
    }

    @CrossOrigin(origins = "http://locahost:4200")
    @GetMapping
    public ResponseEntity<List<BloodDonorForm>> getAll() {
        List<BloodDonorForm> bloodDonorForm = bloodDonorService.findAll();
        return new ResponseEntity<>(bloodDonorForm, HttpStatus.OK);

    }
    @CrossOrigin(origins = "http://locahost:4200")
    @GetMapping("/check/{id}")
    public boolean checkIfAllowed(@PathVariable int id) {
        return bloodDonorService.checkIfAllowed(id);
    }

    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody BloodDonorCreationDTO bloodDonorCreationDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            System.err.println("Error creating new blood donor!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
//            service.create(userCreationDTO);
//            return new ResponseEntity<>(HttpStatus.CREATED);
            BloodDonorForm bloodDonorForm = bloodDonorService.create(bloodDonorCreationDTO);
            return new ResponseEntity<>(bloodDonorForm, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
