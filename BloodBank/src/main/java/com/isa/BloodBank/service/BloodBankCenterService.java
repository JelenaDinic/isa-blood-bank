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
    public BloodBankCreationDTO getById(int id) {
        BloodBankCenter bloodBankCenter = bloodBankCenterRepository.findBloodBankCenterById(id);
        BloodBankCreationDTO bloodBankCreationDTO = new BloodBankCreationDTO(bloodBankCenter);
        return bloodBankCreationDTO;
    }
    public void create(BloodBankCreationDTO bloodBankCenterDTO){
        try {
            BloodBankCenter bloodBankCenter = new BloodBankCenter(bloodBankCenterDTO);

            Address address = new Address(bloodBankCenterDTO.getStreet(), bloodBankCenterDTO.getNumber(), bloodBankCenterDTO.getCity(), bloodBankCenterDTO.getCountry());
            addressRepository.save(address);

            bloodBankCenter.setAddress(address);

            bloodBankCenterRepository.save(bloodBankCenter);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Can't save blood bank!");
        }

    }
    public void update(BloodBankCreationDTO bloodBankDTO) {
        BloodBankCenter bloodBankCenter = bloodBankCenterRepository.findBloodBankCenterById(bloodBankDTO.getId());
        Address address = addressRepository.findAddressById(bloodBankDTO.getAddressId());
        address.setStreet(bloodBankDTO.getStreet());
        address.setNumber(bloodBankDTO.getNumber());
        address.setCity(bloodBankDTO.getCity());
        address.setCountry(bloodBankDTO.getCountry());
        addressRepository.save(address);

        bloodBankCenter.setAddress(address);
        bloodBankCenter.setName(bloodBankDTO.getName());
        bloodBankCenter.setDescription(bloodBankDTO.getDescription());
        bloodBankCenterRepository.save(bloodBankCenter);
    }

    public List<BloodBankCenter> findAll() {
        return bloodBankCenterRepository.findAll();
    }

}
