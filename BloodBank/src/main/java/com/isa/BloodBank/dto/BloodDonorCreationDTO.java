package com.isa.BloodBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BloodDonorCreationDTO {

    private String isUnacceptableWeight;
    private String isHealthy;
    private String hasSkinProblems;
    private String hasUnacceptablePressure;
    private String isUnderTreatment;
    private String isOnPeriod;
    private String hasTattoo;

}
