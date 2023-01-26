package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.PasswordChangeDTO;
import com.isa.BloodBank.dto.SysadminCreationDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.AddressRepository;
import com.isa.BloodBank.repository.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SystemAdminService {

    private final SystemAdminRepository repository;
    private final AddressRepository addressRepository;

    @Autowired
    public SystemAdminService(SystemAdminRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void create(SysadminCreationDTO sysadminCreationDTO) {
        try{
            SystemAdmin systemAdmin = new SystemAdmin(sysadminCreationDTO);

            Address address = new Address(sysadminCreationDTO.getStreet(), sysadminCreationDTO.getNumber(), sysadminCreationDTO.getCity(), sysadminCreationDTO.getCountry());
            addressRepository.save(address);

            systemAdmin.setAddress(address);

            repository.save(systemAdmin);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Can't save staff!");
        }
    }

    public List<SystemAdmin> findAll() {
        return repository.findAll();
    }

    public void changePassword(String newPassword, int  userId) {
        SystemAdmin systemAdmin = repository.findById(userId);

        systemAdmin.setPassword(newPassword);
        systemAdmin.setRequiresPasswordChange(false);

        repository.save(systemAdmin);
    }

    public SystemAdmin getById(int id) {
        return repository.findById(id);
    }

}
