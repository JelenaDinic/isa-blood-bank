package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.model.SystemAdmin;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDisplayDTO {
    private int id;
    private String name;
    private String email;
    private String role;
    private String address;
    private String dob;
    private String phoneNumber;
    private String bloodBankName;
    private String bloodType;
    private String gender;
    private String personalId;

    public UserDisplayDTO(RegisteredUser registeredUser) {
        this.setName(registeredUser.getFirstName() + " " + registeredUser.getLastName());
        this.setAddress(registeredUser.getAddress().toString());
        this.setDob(registeredUser.getDob().toString());
        this.setPhoneNumber(registeredUser.getPhoneNumber());
        this.setEmail(registeredUser.getEmail());
        this.setRole("Regular user");
//        switch (registeredUser.getBloodType()) {
//            case A_NEG: this.setBloodType("A-");
//                break;
//            case A_POS: this.setBloodType("A+");
//                break;
//            case B_NEG: this.setBloodType("B-");
//                break;
//            case B_POS: this.setBloodType("B+");
//                break;
//            case AB_NEG: this.setBloodType("AB-");
//                break;
//            case AB_POS: this.setBloodType("AB+");
//                break;
//            case ZERO_NEG: this.setBloodType("0-");
//                break;
//            case ZERO_POS: this.setBloodType("0+");
//                break;
//        }
        this.setGender(registeredUser.getGender());
        this.setPersonalId(registeredUser.getPersonalId().toString());
    }

    public UserDisplayDTO(Staff staff) {
        this.setName(staff.getFirstName() + " " + staff.getLastName());
        this.setAddress(staff.getAddress().toString());
        this.setDob(staff.getDob().toString());
        this.setPhoneNumber(staff.getPhoneNumber());
        this.setEmail(staff.getEmail());
        this.setRole("Staff");
        this.setGender(staff.getGender());
        this.setPersonalId(staff.getPersonalId().toString());
    }

    public UserDisplayDTO(SystemAdmin systemAdmin) {
        this.setName(systemAdmin.getFirstName() + " " + systemAdmin.getLastName());
        this.setAddress(systemAdmin.getAddress().toString());
        this.setDob(systemAdmin.getDob().toString());
        this.setPhoneNumber(systemAdmin.getPhoneNumber());
        this.setEmail(systemAdmin.getEmail());
        this.setRole("System administrator");
        this.setGender(systemAdmin.getGender());
        this.setPersonalId(systemAdmin.getPersonalId().toString());
    }

    public UserDisplayDTO(Person person) {
        this.setId(person.getId());
        this.setName(person.getFirstName() + " " + person.getLastName());
        this.setAddress(person.getAddress().toString());
        this.setDob(person.getDob().toString());
        this.setPhoneNumber(person.getPhoneNumber());
        this.setEmail(person.getEmail());
        this.setRole(person.getRole().toString());
        this.setGender(person.getGender());
        this.setPersonalId(person.getPersonalId().toString());
    }

}


