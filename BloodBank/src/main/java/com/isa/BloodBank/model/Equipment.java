package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="equipment")
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private EquipmentType type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bloodBankCenter_id", referencedColumnName = "id", nullable= true)
    private BloodBankCenter bloodBankCenter;
    private int quantity;
}
