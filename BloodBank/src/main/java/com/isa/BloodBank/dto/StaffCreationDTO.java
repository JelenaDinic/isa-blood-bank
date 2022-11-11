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
    private String fullName;
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

    public StaffCreationDTO(Staff staff) {
        this.setFullName(staff.getFirstName() + " " + staff.getLastName());
    }
}
