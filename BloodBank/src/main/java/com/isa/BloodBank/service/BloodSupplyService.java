package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.dto.BloodSupplyDTO;
import com.isa.BloodBank.model.AppointmentReport;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.BloodSupply;
import com.isa.BloodBank.repository.BloodSupplyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<BloodSupplyDTO> getByCenterId(int id) {
        List<BloodSupply> bloodSupplies = bloodSupplyRepository.findByBloodBankId(id);
        List<BloodSupplyDTO> bloodSupplyDTOS = new ArrayList<BloodSupplyDTO>();
        for(BloodSupply bs : bloodSupplies) {
            BloodSupplyDTO dto = new BloodSupplyDTO(bs);
            bloodSupplyDTOS.add(dto);
        }

        return bloodSupplyDTOS;
    }
}
