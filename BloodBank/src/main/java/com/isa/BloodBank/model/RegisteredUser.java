package com.isa.BloodBank.model;

import com.isa.BloodBank.dto.UserCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registeredUsers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisteredUser extends Person {

    private BloodType bloodType;
    private String profession;
    private String professionInfo;

    public RegisteredUser(UserCreationDTO dto){
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setDob(dto.getDob());
        this.setEmail(dto.getEmail());
//        this.setAddress(dto.getAddress());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setPassword(dto.getPassword());
        this.setRole(UserRole.USER);
        this.setProfession(dto.getProfession());
        this.setProfessionInfo(dto.getProfessionInfo());
        this.setPersonalId(dto.getPersonalId());
    }
}
