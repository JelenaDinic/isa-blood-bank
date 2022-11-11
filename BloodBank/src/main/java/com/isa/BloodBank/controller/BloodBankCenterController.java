package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.service.BloodBankCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/blood-bank-center")
public class BloodBankCenterController {
    private final BloodBankCenterService bloodBankCenterService;
    @Autowired
    public BloodBankCenterController(BloodBankCenterService bloodBankCenterService) {
        this.bloodBankCenterService = bloodBankCenterService;
    }
    @PostMapping
    public void create(@RequestBody BloodBankCreationDTO bloodBankCenter) {
        bloodBankCenterService.create(bloodBankCenter);
    }
    @GetMapping("/{id}")
    public Optional<BloodBankCenter> getById(@PathVariable("id") int id) {
        return bloodBankCenterService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<BloodBankCenter>> getAll() {
        List<BloodBankCenter> bloodBankCenters = bloodBankCenterService.findAll();
        return new ResponseEntity<>(bloodBankCenters, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody BloodBankCenter bloodBankCenter) {
        //provjeriti poklapanje id-a
        bloodBankCenterService.update(bloodBankCenter);
    }
}
