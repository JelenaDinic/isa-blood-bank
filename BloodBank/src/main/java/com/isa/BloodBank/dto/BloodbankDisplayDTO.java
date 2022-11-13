package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.BloodBankCenter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BloodbankDisplayDTO {
    private int id;
    private String name;
    private String street;
    private String number;
    private String city;
    private String country;
    private String description;
    private double averageGrade;

    public BloodbankDisplayDTO(BloodBankCenter bloodBankCenter){
        this.setName(bloodBankCenter.getName());
        this.setId(bloodBankCenter.getId());
        this.setStreet(bloodBankCenter.getAddress().getStreet());
        this.setNumber(bloodBankCenter.getAddress().getNumber());
        this.setCity(bloodBankCenter.getAddress().getCity());
        this.setCountry(bloodBankCenter.getAddress().getCountry());
        this.setDescription(bloodBankCenter.getDescription());
        this.setAverageGrade(bloodBankCenter.getAverageGrade());
    }
}
