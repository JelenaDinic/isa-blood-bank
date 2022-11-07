package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import com.isa.BloodBank.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;
    private final StaffRepository staffRepository;
    private final BloodBankCenterRepository bloodBankCenterRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository repository, StaffRepository staffRepository, BloodBankCenterRepository bloodBankCenterRepository) {
        this.repository = repository;
        this.staffRepository = staffRepository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
    }

    public RegisteredUser create(RegisteredUser registeredUser) {
        return repository.save(registeredUser);
    }

    public List<RegisteredUser> findAll() {
        return repository.findAll();
    }

    public List<UserDisplayDTO> findAllUsers() {
        List<RegisteredUser> registeredUsers = repository.findAll();
        List<Staff> staff = staffRepository.findAll();

        List<UserDisplayDTO> userDTOs = new ArrayList<>();

        for(RegisteredUser registeredUser : registeredUsers) {
            UserDisplayDTO dto = new UserDisplayDTO(registeredUser);

            userDTOs.add(dto);
        }

        for (Staff staffMember : staff) {
            UserDisplayDTO dto = new UserDisplayDTO(staffMember);
            BloodBankCenter bloodBankCenter = staffMember.getBloodBankCenter();
            dto.setBloodBankName(bloodBankCenter.getName());

            userDTOs.add(dto);
        }

        return userDTOs;
    }

}
