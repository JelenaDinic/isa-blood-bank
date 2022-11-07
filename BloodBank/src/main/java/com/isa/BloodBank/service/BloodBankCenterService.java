package com.isa.BloodBank.service;

import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BloodBankCenterService {
    private final BloodBankCenterRepository bloodBankCenterRepository;

    @Autowired
    public BloodBankCenterService(BloodBankCenterRepository bloodBankCenterRepository) {
        this.bloodBankCenterRepository = bloodBankCenterRepository;
    }
    public Optional<BloodBankCenter> getById(int id) {
        return bloodBankCenterRepository.findById(id);
    }
    public void create(BloodBankCenter bloodBankCenter){
        bloodBankCenterRepository.save(bloodBankCenter);
    }
}
