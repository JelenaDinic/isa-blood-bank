package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.model.Address;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.repository.AddressRepository;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodBankCenterService {
    private final BloodBankCenterRepository bloodBankCenterRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public BloodBankCenterService(BloodBankCenterRepository bloodBankCenterRepository, AddressRepository addressRepository) {
        this.bloodBankCenterRepository = bloodBankCenterRepository;
        this.addressRepository = addressRepository;
    }
    public Optional<BloodBankCenter> getById(int id) {
        return bloodBankCenterRepository.findById(id);
    }
    public void create(BloodBankCreationDTO bloodBankCenterDTO){
        BloodBankCenter bloodBankCenter = new BloodBankCenter(bloodBankCenterDTO);

        Address address = new Address(bloodBankCenterDTO.getStreet(), bloodBankCenterDTO.getNumber(), bloodBankCenterDTO.getCity(), bloodBankCenterDTO.getCountry());
        addressRepository.save(address);

        bloodBankCenter.setAddress(address);

        bloodBankCenterRepository.save(bloodBankCenter);
    }
    public void update(BloodBankCenter bloodBankCenter) {
        bloodBankCenterRepository.save(bloodBankCenter);
    }

    public List<BloodBankCenter> findAll() {
        return bloodBankCenterRepository.findAll();
    }

}
