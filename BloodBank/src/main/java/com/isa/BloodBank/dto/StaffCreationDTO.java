package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffCreationDTO {
    private int id;
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
    private Long personalId;
    private String gender;
    private int addressId;

    public StaffCreationDTO(Staff staff) {
        this.setId(staff.getId());
        this.setFirstName(staff.getFirstName());
        this.setLastName(staff.getLastName());
        this.setEmail(staff.getEmail());
        this.setPassword(staff.getPassword());
        this.setRole(staff.getRole());
        this.setStreet(staff.getAddress().getStreet());
        this.setNumber(staff.getAddress().getNumber());
        this.setCity(staff.getAddress().getCity());
        this.setCountry(staff.getAddress().getCountry());
        this.setDob(staff.getDob());
        this.setPersonalId(staff.getPersonalId());
        this.setBloodBankId(staff.getBloodBankCenter().getId());
        this.setPhoneNumber(staff.getPhoneNumber());
        this.setGender(staff.getGender());
        this.setAddressId(staff.getAddress().getId());

    }
}
