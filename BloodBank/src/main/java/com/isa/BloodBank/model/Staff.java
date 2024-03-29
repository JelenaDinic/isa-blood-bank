package com.isa.BloodBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.BloodBank.dto.StaffCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends Person{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bloodBankCenter_id", nullable=false)
    private BloodBankCenter bloodBankCenter;
    @OneToMany(mappedBy = "staffMember",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Complaint> complaints;
    @OneToMany(mappedBy="staff", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FreeExamination> freeExaminations;

    public Staff(StaffCreationDTO dto) {
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setDob(dto.getDob());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setPassword(dto.getPassword());
        this.setRole(dto.getRole());
        this.setGender(dto.getGender());
        this.setPersonalId(dto.getPersonalId());
    }
}
