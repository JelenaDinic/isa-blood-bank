package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.model.Address;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.UnregisteredUser;
import com.isa.BloodBank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UnregisteredUserService {

    private final UnregisteredUserRepository unregisteredUserRepository;

    @Autowired
    public UnregisteredUserService(UnregisteredUserRepository unregisteredUserRepository) {
        this.unregisteredUserRepository = unregisteredUserRepository;
    }

    public UnregisteredUser create(UserCreationDTO userCreationDTO) {
        UnregisteredUser unregisteredUser = new UnregisteredUser(userCreationDTO);

        Address address = new Address(userCreationDTO.getStreet(), userCreationDTO.getNumber(), userCreationDTO.getCity(), userCreationDTO.getCountry());

        //gen act code
        String activationCode = UUID.randomUUID().toString();
        unregisteredUser.setActivationCode(activationCode);

        unregisteredUser.setAddress(address);
        System.out.println(unregisteredUser);
        return unregisteredUserRepository.save(unregisteredUser);
    }

    public void delete(UnregisteredUser unregisteredUser){
        unregisteredUserRepository.delete(unregisteredUser);
    }

}
