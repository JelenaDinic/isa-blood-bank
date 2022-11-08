package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.model.SystemAdmin;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import com.isa.BloodBank.repository.StaffRepository;
import com.isa.BloodBank.repository.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;
    private final StaffRepository staffRepository;
    private final BloodBankCenterRepository bloodBankCenterRepository;
    private final SystemAdminRepository systemAdminRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository repository, StaffRepository staffRepository, BloodBankCenterRepository bloodBankCenterRepository, SystemAdminRepository systemAdminRepository) {
        this.repository = repository;
        this.staffRepository = staffRepository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
        this.systemAdminRepository = systemAdminRepository;
    }

    public RegisteredUser create(RegisteredUser registeredUser) {
        return repository.save(registeredUser);
    }

    public List<RegisteredUser> findAll() {
        return repository.findAll();
    }

    public List<UserDisplayDTO> findAllUsers() {
        List<UserDisplayDTO> userDTOs = new ArrayList<>();

        for(RegisteredUser registeredUser : repository.findAll()) {
            UserDisplayDTO dto = new UserDisplayDTO(registeredUser);

            userDTOs.add(dto);
        }

        for (Staff staffMember : staffRepository.findAll()) {
            UserDisplayDTO dto = new UserDisplayDTO(staffMember);
            BloodBankCenter bloodBankCenter = staffMember.getBloodBankCenter();
            dto.setBloodBankName(bloodBankCenter.getName());

            userDTOs.add(dto);
        }

        for(SystemAdmin systemAdmin : systemAdminRepository.findAll()) {
            UserDisplayDTO dto = new UserDisplayDTO(systemAdmin);

            userDTOs.add(dto);
        }

        return userDTOs;
    }

}
