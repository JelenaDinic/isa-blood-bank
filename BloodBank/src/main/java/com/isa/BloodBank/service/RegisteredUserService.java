package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.dto.UserProfileDisplayDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isa.BloodBank.model.Address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;
    private final StaffRepository staffRepository;
    private final BloodBankCenterRepository bloodBankCenterRepository;
    private final AddressRepository addressRepository;
    private final SystemAdminRepository systemAdminRepository;
    private final UserRepository userRepository;

    private final UnregisteredUserService unregisteredUserService;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository repository, StaffRepository staffRepository, BloodBankCenterRepository bloodBankCenterRepository, SystemAdminRepository systemAdminRepository, AddressRepository addressRepository, UserRepository userRepository, UnregisteredUserService unregisteredUserService) {
        this.repository = repository;
        this.staffRepository = staffRepository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
        this.systemAdminRepository = systemAdminRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.unregisteredUserService = unregisteredUserService;
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

    public List<UserDisplayDTO> findAllUsers(Pageable page) {
        List<UserDisplayDTO> userDTOs= new ArrayList<>();

        for(Person person : userRepository.findAll(page)) {
            UserDisplayDTO dto = new UserDisplayDTO(person);
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

    public RegisteredUser findById(int id) {
        return repository.findRegisteredUserById(id);
    }

    private void updateUser(RegisteredUser user, UserProfileDisplayDTO userProfileDTO){
        user.setFirstName(userProfileDTO.getFirstName());
        user.setLastName(userProfileDTO.getLastName());
        user.setGender(userProfileDTO.getGender());
        Address address = addressRepository.findAddressById(userProfileDTO.getAddressId());
        address.setStreet(userProfileDTO.getStreet());
        address.setNumber(userProfileDTO.getNumber());
        address.setCity(userProfileDTO.getCity());
        address.setCountry(userProfileDTO.getCountry());
        addressRepository.save(address);
        user.setAddress(address);
        user.setPhoneNumber(userProfileDTO.getPhoneNumber());
        user.setProfession(userProfileDTO.getProfession());
        user.setProfessionInfo(userProfileDTO.getProfessionInfo());
        //user.setDob(new Date(userProfileDTO.getDateOfBirth()));
    }

    public List<UserDisplayDTO> searchUsers(Pageable page, String searchText) {
        Page<Person> personPage = userRepository.findAllByFirstNameOrLastName(searchText, page);

        List<UserDisplayDTO> userDTOs = new ArrayList<>();

        for(Person person : personPage) {
            UserDisplayDTO dto = new UserDisplayDTO(person);
            userDTOs.add(dto);
        }

        return userDTOs;
    }
    public void addPenalty(int id) {
        RegisteredUser user  = repository.findRegisteredUserById(id);
        user.setPenalties(user.getPenalties() + 1);
        userRepository.save(user);
    }

    public void codeVerification(String activationCode) {
        UnregisteredUser newUser = unregisteredUserService.findByActivationCode(activationCode);
        Person registeredUser = new Person();
        UserCreationDTO dto = new UserCreationDTO();

        dto.setFirstName(newUser.getFirstName());
        dto.setLastName(newUser.getLastName());
        dto.setEmail(newUser.getEmail());
        dto.setPassword(newUser.getPassword());
        dto.setRole(newUser.getRole());
        dto.setDob(newUser.getDob());
        dto.setPhoneNumber(newUser.getPhoneNumber());
        dto.setGender(newUser.getGender());
        dto.setPersonalId(newUser.getPersonalId());
        dto.setCity(newUser.getAddress().getCity());
        dto.setCountry(newUser.getAddress().getCountry());
        dto.setStreet(newUser.getAddress().getStreet());
        dto.setNumber(newUser.getAddress().getNumber());

        create(dto);
        unregisteredUserService.delete(newUser);
    }

    public int getSearchResultCount(String search) {
        List<Person> users = userRepository.search(search.toLowerCase());
        return users.size();
    }
}
