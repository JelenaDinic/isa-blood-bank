package com.isa.BloodBank.model;

import com.isa.BloodBank.dto.BloodDonorCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "bloodDonorForms")
@Table(name = "bloodDonorForms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BloodDonorForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private RegisteredUser registeredUser;
    private boolean isUnacceptableWeight;
    private boolean isUnhealthy;
    private boolean hasSkinProblems;
    private boolean hasUnacceptablePressure;
    private boolean isUnderTreatment;
    private boolean isOnPeriod;
    private boolean hasTattoo;
    private boolean isRested;
    private boolean isEpileptic;
    private boolean hasAllergies;
    private boolean hasDrankAlcohol;
    private boolean isPregnant;

    private LocalDate dateOfFormFilling;

    public BloodDonorForm(BloodDonorCreationDTO dto, RegisteredUser registredUser){
        this.setUnacceptableWeight(Boolean.parseBoolean(dto.getIsUnacceptableWeight()));
        this.setUnhealthy(Boolean.parseBoolean(dto.getIsUnhealthy()));
        this.setHasSkinProblems(Boolean.parseBoolean(dto.getHasSkinProblems()));
        this.setHasUnacceptablePressure(Boolean.parseBoolean(dto.getHasUnacceptablePressure()));
        this.setUnderTreatment(Boolean.parseBoolean(dto.getIsUnderTreatment()));
        this.setOnPeriod(false);
        this.setHasTattoo(Boolean.parseBoolean(dto.getHasTattoo()));
        this.setRested(Boolean.parseBoolean(dto.getIsRested()));
        this.setEpileptic(Boolean.parseBoolean(dto.getIsEpileptic()));
        this.setHasAllergies(Boolean.parseBoolean(dto.getHasAllergies()));
        this.setHasDrankAlcohol(Boolean.parseBoolean(dto.getHasDrankAlcohol()));
        this.setPregnant(false);
        this.setRegisteredUser(registredUser);
    }

}
