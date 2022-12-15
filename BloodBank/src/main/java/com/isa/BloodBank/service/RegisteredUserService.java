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

import javax.transaction.Transactional;
import java.util.*;

import static com.isa.BloodBank.model.UserRole.USER;
import static java.sql.DriverManager.println;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;
    private final StaffRepository staffRepository;
    private final BloodBankCenterRepository bloodBankCenterRepository;
    private final AddressRepository addressRepository;
    private final SystemAdminRepository systemAdminRepository;
    private final UserRepository userRepository;
    private final VerificationTokenService verificationTokenService;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository repository, StaffRepository staffRepository,
                                 BloodBankCenterRepository bloodBankCenterRepository,
                                 SystemAdminRepository systemAdminRepository, AddressRepository addressRepository,
                                 UserRepository userRepository, VerificationTokenService verificationTokenService) {
        this.repository = repository;
        this.staffRepository = staffRepository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
        this.systemAdminRepository = systemAdminRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.verificationTokenService = verificationTokenService;
    }

//    @Transactional
//    public RegisteredUser save(RegisteredUser user){
//        return repository.save(user);
//    }
    public RegisteredUser create(UserCreationDTO userCreationDTO) {
        RegisteredUser registeredUser = new RegisteredUser(userCreationDTO);

        Address address = new Address(userCreationDTO.getStreet(), userCreationDTO.getNumber(), userCreationDTO.getCity(), userCreationDTO.getCountry());

        registeredUser.setAddress(address);
        System.out.println(registeredUser);

        Optional<RegisteredUser> saved = Optional.of(repository.save(registeredUser));
        //return repository.save(registeredUser);

        saved.ifPresent( u ->{
            try{
                String token = UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(), token);
                println("uslo je ovde");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
        return saved.get();
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

    public List<UserDisplayDTO> searchUsers(Pageable page, String searchText) {
        Page<Person> personPage = userRepository.findAllByFirstNameOrLastName(searchText, page);

        List<UserDisplayDTO> userDTOs = new ArrayList<>();

        for(Person person : personPage) {
            UserDisplayDTO dto = new UserDisplayDTO(person);
            userDTOs.add(dto);
        }

        return userDTOs;
    }

}
