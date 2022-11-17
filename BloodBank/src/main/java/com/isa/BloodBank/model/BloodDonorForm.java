package com.isa.BloodBank.model;

import com.isa.BloodBank.dto.BloodDonorCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bloodDonorForm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BloodDonorForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    //private RegisteredUser registeredUser;
    private boolean isUnacceptableWeight;
    private boolean isHealthy;
    private boolean hasSkinProblems;
    private boolean hasUnacceptablePressure;
    private boolean isUnderTreatment;
    private boolean isOnPeriod;
    private boolean hasTattoo;
    private LocalDate dateOfFormFilling;

    public BloodDonorForm(BloodDonorCreationDTO dto){
        this.setUnacceptableWeight(Boolean.parseBoolean(dto.getIsUnacceptableWeight()));
        this.setHealthy(Boolean.parseBoolean(dto.getIsHealthy()));
        this.setHasSkinProblems(Boolean.parseBoolean(dto.getHasSkinProblems()));
        this.setHasUnacceptablePressure(Boolean.parseBoolean(dto.getHasUnacceptablePressure()));
        this.setUnderTreatment(Boolean.parseBoolean(dto.getIsUnderTreatment()));
        this.setOnPeriod(Boolean.parseBoolean(dto.getIsOnPeriod()));
        this.setHasTattoo(Boolean.parseBoolean(dto.getHasTattoo()));
    }

}
