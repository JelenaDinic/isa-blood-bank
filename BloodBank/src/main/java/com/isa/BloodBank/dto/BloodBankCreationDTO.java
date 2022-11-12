package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.BloodBankCenter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankCreationDTO {
    private int id;
    private String name;
    private String street;
    private String number;
    private String city;
    private String country;
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
