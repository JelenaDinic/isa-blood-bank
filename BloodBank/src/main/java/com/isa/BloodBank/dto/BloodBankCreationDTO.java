package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.BloodBankCenter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankCreationDTO {
    private int id;

    @NotNull
    @NotBlank(message = "Bank name cannot be blank")
    @Pattern(regexp="([A-Z][a-z]+)(\\s[A-Z]?[a-z]+)*", message="Invalid bank name input!")
    private String name;

    @NotNull
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotNull
    @NotBlank(message = "Street number cannot be blank")
    @Pattern(regexp="([0-9]{1,3})[A-Z]?", message="Invalid street number input!")
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
    private double averageGrade;
    private int addressId;

    public BloodBankCreationDTO(BloodBankCenter bloodBankCenter) {
        this.setId(bloodBankCenter.getId());
        this.setName(bloodBankCenter.getName());
        this.setDescription(bloodBankCenter.getDescription());
        this.setStreet(bloodBankCenter.getAddress().getStreet());
        this.setNumber(bloodBankCenter.getAddress().getNumber());
        this.setCity(bloodBankCenter.getAddress().getCity());
        this.setCountry(bloodBankCenter.getAddress().getCountry());
        this.setAverageGrade(bloodBankCenter.getAverageGrade());
        this.setAddressId(bloodBankCenter.getAddress().getId());
    }
}
