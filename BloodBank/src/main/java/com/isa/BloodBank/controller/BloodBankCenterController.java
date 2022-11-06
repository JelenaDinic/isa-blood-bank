package com.isa.BloodBank.controller;

import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.service.BloodBankCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/blood-bank")
public class BloodBankCenterController {
    private final BloodBankCenterService bloodBankCenterService;
    @Autowired
    public BloodBankCenterController(BloodBankCenterService bloodBankCenterService) {
        this.bloodBankCenterService = bloodBankCenterService;
    }
    @PostMapping
    public void create(@RequestBody BloodBankCenter bloodBankCenter) {
        bloodBankCenterService.create(bloodBankCenter);
    }

}
