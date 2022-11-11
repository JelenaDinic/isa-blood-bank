package com.isa.BloodBank.model;

import com.isa.BloodBank.dto.StaffCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends Person{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="bloodBankCenter_id", nullable=false)
    private BloodBankCenter bloodBankCenter;

    public Staff(StaffCreationDTO dto) {
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setDob(dto.getDob());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setPassword(dto.getPassword());
        this.setRole(dto.getRole());
    }
}
