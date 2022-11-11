package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffCreationDTO {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private String address;
    private LocalDate dob;
    private String phoneNumber;
    private int bloodBankId;

    public StaffCreationDTO(Staff staff) {
        this.setFullName(staff.getFirstName() + " " + staff.getLastName());
    }
}
