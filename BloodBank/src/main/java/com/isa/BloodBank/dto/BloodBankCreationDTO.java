package com.isa.BloodBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankCreationDTO {
    private String name;
    private String street;
    private String number;
    private String city;
    private String country;
    private String description;
}
