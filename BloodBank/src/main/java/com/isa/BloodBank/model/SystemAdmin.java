package com.isa.BloodBank.model;

import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.dto.SysadminCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "systemAdministrators")
@Getter
@Setter
public class SystemAdmin extends Person {

    @Column(nullable = true)
    private boolean requiresPasswordChange;

    public SystemAdmin(SysadminCreationDTO dto) {
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setDob(dto.getDob());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setPassword(dto.getPassword());
        this.setRole(dto.getRole());
        this.setGender(dto.getGender());
        this.setPersonalId(dto.getPersonalId());
        this.setRequiresPasswordChange(true);
    }

    public SystemAdmin() {

    }
}
