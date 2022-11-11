package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffCreationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private String street;
    private String number;
    private String city;
    private String country;
    private LocalDate dob;
    private String phoneNumber;
    private int bloodBankId;
}
