package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDisplayDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String address;
    private String dob;
    private String phoneNumber;
    private String bloodBankName;
    private String bloodType;

    public UserDisplayDTO(RegisteredUser registeredUser) {
        this.setFirstName(registeredUser.getFirstName());
        this.setLastName(registeredUser.getLastName());
        this.setAddress(registeredUser.getAddress());
        this.setDob(registeredUser.getDob().toString());
        this.setPhoneNumber(registeredUser.getPhoneNumber());
        this.setEmail(registeredUser.getEmail());
        this.setRole("Regular user");
        switch (registeredUser.getBloodType()) {
            case A_NEG: this.setBloodType("A-");
                break;
            case A_POS: this.setBloodType("A+");
                break;
            case B_NEG: this.setBloodType("B-");
                break;
            case B_POS: this.setBloodType("B+");
                break;
            case AB_NEG: this.setBloodType("AB-");
                break;
            case AB_POS: this.setBloodType("AB+");
                break;
            case ZERO_NEG: this.setBloodType("0-");
                break;
            case ZERO_POS: this.setBloodType("0+");
                break;
        }
    }

    public UserDisplayDTO(Staff staff) {
        this.setFirstName(staff.getFirstName());
        this.setLastName(staff.getLastName());
        this.setAddress(staff.getAddress());
        this.setDob(staff.getDob().toString());
        this.setPhoneNumber(staff.getPhoneNumber());
        this.setEmail(staff.getEmail());
        this.setRole("Staff");
    }

}


