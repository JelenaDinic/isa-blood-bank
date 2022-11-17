package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.dto.UserProfileDisplayDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isa.BloodBank.model.Address;

import java.util.ArrayList;
import java.util.Date;
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

        registeredUser.setAddress(address);
        System.out.println(registeredUser);
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

    public UserProfileDisplayDTO findByEmailDTO(String email){
        List<RegisteredUser> list = findAll();
        for(RegisteredUser user : list){
            if(user.getEmail().equals(email)){
                return new UserProfileDisplayDTO(user);
            }
        }
        return null;
    }

    public UserProfileDisplayDTO update(UserProfileDisplayDTO userProfileDTO){
        RegisteredUser user = findByEmail(userProfileDTO.getEmail());
        updateUser(user, userProfileDTO);
        repository.save(user);
        return new UserProfileDisplayDTO(user);
    }

    private RegisteredUser findByEmail(String email){
        List<RegisteredUser> users = findAll();
        for(RegisteredUser user : users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    private void updateUser(RegisteredUser user, UserProfileDisplayDTO userProfileDTO){
        user.setFirstName(userProfileDTO.getName());
        user.setLastName(userProfileDTO.getSurname());
        user.setGender(userProfileDTO.getGender());
        Address address = addressRepository.findAddressById(userProfileDTO.getAddressId());
        address.setStreet(userProfileDTO.getStreet());
        address.setNumber(userProfileDTO.getNumber());
        address.setCity(userProfileDTO.getCity());
        address.setCountry(userProfileDTO.getCountry());
        user.setPhoneNumber(userProfileDTO.getPhoneNumber());
        user.setProfession(userProfileDTO.getProfession());
        user.setProfessionInfo(userProfileDTO.getProfessionInfo());
        //user.setDob(new Date(userProfileDTO.getDateOfBirth()));
    }

}
