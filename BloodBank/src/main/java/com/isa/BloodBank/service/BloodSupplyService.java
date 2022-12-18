package com.isa.BloodBank.service;

import com.isa.BloodBank.model.AppointmentReport;
import com.isa.BloodBank.model.BloodSupply;
import com.isa.BloodBank.repository.BloodSupplyRepository;
import org.springframework.stereotype.Service;

@Service
public class BloodSupplyService {
    private final BloodSupplyRepository bloodSupplyRepository;

    public BloodSupplyService(BloodSupplyRepository bloodSupplyRepository) {
        this.bloodSupplyRepository = bloodSupplyRepository;
    }

    public void updateAmount(AppointmentReport report) {
        BloodSupply bloodSupply = bloodSupplyRepository.getBloodSupplyByBloodType(report.getBloodType());
        bloodSupply.setAmount(bloodSupply.getAmount() + report.getBloodAmount());
        bloodSupplyRepository.save(bloodSupply);
    }
}
