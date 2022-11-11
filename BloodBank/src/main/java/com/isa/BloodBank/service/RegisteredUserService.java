package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.isa.BloodBank.model.UserRole.USER;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;
    private final StaffRepository staffRepository;
    private final BloodBankCenterRepository bloodBankCenterRepository;
    private final AddressRepository addressRepository;
    private final SystemAdminRepository systemAdminRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository repository, StaffRepository staffRepository, BloodBankCenterRepository bloodBankCenterRepository, SystemAdminRepository systemAdminRepository, AddressRepository addressRepository) {
        this.repository = repository;
        this.staffRepository = staffRepository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
        this.systemAdminRepository = systemAdminRepository;
        this.addressRepository = addressRepository;
    }

    public RegisteredUser create(UserCreationDTO userCreationDTO) {
        RegisteredUser registeredUser = new RegisteredUser(userCreationDTO);

        Address address = new Address(userCreationDTO.getStreet(), userCreationDTO.getNumber(), userCreationDTO.getCity(), userCreationDTO.getCountry());

        addressRepository.save(address);

        registeredUser.setAddress(address);
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
