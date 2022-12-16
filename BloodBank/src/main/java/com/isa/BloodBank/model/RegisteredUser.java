package com.isa.BloodBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.BloodBank.dto.UserCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "registeredUsers")
@Table(name = "registeredUsers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisteredUser extends Person {

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BloodDonorForm> bloodDonorForms;
    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AppointmentReport> appointmentReports;
    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments;
    private BloodType bloodType;
    private String profession;
    private String professionInfo;
    private int penalties;


    public RegisteredUser(UserCreationDTO dto){
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setDob(dto.getDob());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setPassword(dto.getPassword());
//        this.setRole(UserRole.USER);
        this.setProfession(dto.getProfession());
        this.setProfessionInfo(dto.getProfessionInfo());
        this.setPersonalId(dto.getPersonalId());
        this.setGender(dto.getGender());
    }
}
