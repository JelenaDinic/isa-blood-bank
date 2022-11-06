package com.isa.BloodBank.service;

import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodBankCenterService {
    private final BloodBankCenterRepository bloodBankCenterRepository;

    @Autowired
    public BloodBankCenterService(BloodBankCenterRepository bloodBankCenterRepository) {
        this.bloodBankCenterRepository = bloodBankCenterRepository;
    }
    public BloodBankCenter getById(int id) {
        return bloodBankCenterRepository.getReferenceById(id);
    }
    public void create(BloodBankCenter bloodBankCenter){
        bloodBankCenterRepository.save(bloodBankCenter);
    }
}
