package com.isa.BloodBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.BloodBank.dto.UserCreationDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


//@Entity(name = "unregisteredUsers")
//@Table(name = "unregisteredUsers")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class UnregisteredUser extends Person {
//
////    @OneToMany(mappedBy = "unregisteredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @JsonIgnore
////    private Set<BloodDonorForm> bloodDonorForms;
////    @OneToMany(mappedBy = "unregisteredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @JsonIgnore
////    private Set<AppointmentReport> appointmentReports;
////    @OneToMany(mappedBy = "unregisteredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @JsonIgnore
////    private Set<Appointment> appointments;
////    private BloodType bloodType;
////    private String profession;
////    private String professionInfo;
////    private int penalties;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="unregisteredUsers")
@Inheritance(strategy = InheritanceType.JOINED)
public class UnregisteredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private UserRole role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    private LocalDate dob;
    private String phoneNumber;
    private String gender;
    @Column(unique = true)
    private Long personalId;

    private String activationCode;


    public UnregisteredUser(UserCreationDTO dto) {
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setDob(dto.getDob());
        this.setEmail(dto.getEmail());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setPassword(dto.getPassword());
        this.setRole(UserRole.USER);
//        this.setProfession(dto.getProfession());
//        this.setProfessionInfo(dto.getProfessionInfo());
        this.setPersonalId(dto.getPersonalId());
        this.setGender(dto.getGender());
    }
}