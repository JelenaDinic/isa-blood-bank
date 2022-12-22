package com.isa.BloodBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BloodDonorCreationDTO {

    private int userId;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isUnacceptableWeight;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isUnhealthy;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String hasSkinProblems;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String hasUnacceptablePressure;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isUnderTreatment;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isOnPeriod;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String hasTattoo;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isRested;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isEpileptic;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String hasAllergies;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String hasDrankAlcohol;

    @NotNull
    @NotBlank(message = "You must choose one answer")
    private String isPregnant;

}
