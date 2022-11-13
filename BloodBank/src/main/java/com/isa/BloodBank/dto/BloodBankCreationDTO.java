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
public class BloodBankCreationDTO {
    @NotNull
    @NotBlank(message = "Bank name cannot be blank")
    private String name;

    @NotNull
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotNull
    @NotBlank(message = "Street number cannot be blank")
    private String number;

    @NotNull
    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotNull
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotNull
    @NotBlank(message = "Description cannot be blank")
    private String description;
}
