package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="bloodSupplies")
@Table(name = "bloodSupplies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BloodSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne
    @JoinColumn(name="bloodBankCenter_id",referencedColumnName = "id", nullable= true)
    private BloodBankCenter bloodBankCenter;
    private int amount;
    private BloodType bloodType;
}
