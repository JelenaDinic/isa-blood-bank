package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.BloodSupply;
import com.isa.BloodBank.model.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodSupplyDTO {
    private String bloodType;
    private int amount;
    public BloodSupplyDTO(BloodSupply bloodSupply) {
        setAmount(bloodSupply.getAmount());
        if(bloodSupply.getBloodType() == BloodType.A_POS)
            setBloodType("A+");
        else if(bloodSupply.getBloodType() == BloodType.A_NEG)
            setBloodType("A-");
        else if(bloodSupply.getBloodType() == BloodType.AB_POS)
            setBloodType("AB+");
        else if(bloodSupply.getBloodType() == BloodType.AB_NEG)
            setBloodType("AB-");
        else if(bloodSupply.getBloodType() == BloodType.B_POS)
            setBloodType("B+");
        else if(bloodSupply.getBloodType() == BloodType.B_NEG)
            setBloodType("B-");
        else if(bloodSupply.getBloodType() == BloodType.ZERO_POS)
            setBloodType("0+");
        else
            setBloodType("0-");
    }

}
