package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.BloodDonorCreationDTO;
import com.isa.BloodBank.model.BloodDonorForm;
import com.isa.BloodBank.service.BloodDonorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/blood-donor")
public class BloodDonorController {

    private BloodDonorService bloodDonorService;

    public BloodDonorController(BloodDonorService bloodDonorService){
        this.bloodDonorService = bloodDonorService;
    }

    @GetMapping
    public ResponseEntity<List<BloodDonorForm>> getAll() {
        List<BloodDonorForm> bloodDonorForm = bloodDonorService.findAll();
        return new ResponseEntity<>(bloodDonorForm, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BloodDonorForm> create(@RequestBody BloodDonorCreationDTO bloodDonorCreationDTO) {
        BloodDonorForm bloodDonorForm = bloodDonorService.create(bloodDonorCreationDTO);
        return new ResponseEntity<>(bloodDonorForm, HttpStatus.CREATED);
    }
}
